package com.dlz.plugin.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.comm.exception.SystemException;
import com.dlz.plugin.quartz.bean.ScheduleJob;
import com.dlz.plugin.quartz.bean.ScheduleJobCron;
import com.dlz.plugin.quartz.bean.ScheduleJobSimple;

/**
 * 提供Job任务相关的方法
 * 
 * @author xiaohe
 */
@Component
@Lazy
public class JobMethod {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(JobMethod.class);
	Scheduler scheduler;
	JobMethod(){
		SchedulerFactory sf = new StdSchedulerFactory();  
        try {
            scheduler = sf.getScheduler();  
            scheduler.start();
        } catch (SchedulerException e) {  
        	logger.error(e.getMessage(), e); 
        }  
	}
	/**
	 * 添加一个任务，如果任务已经存在则更新该任务
	 *
	 * @param job
	 * @throws SchedulerException
	 */
	public void addAndResetScheduleJobOld(ScheduleJob job) {
		if(job==null || job.getJobName()==null){
			throw new SystemException("任务不能为空");
		}
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		Trigger trigger;
		try {
			trigger = scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder.newJob(com.dlz.plugin.quartz.QuartzJobFactory.class).withIdentity(getJobKey(job)).build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(triggerKey);
				if(job instanceof ScheduleJobSimple){
					ScheduleJobSimple jobSimple=(ScheduleJobSimple)job;
					SimpleScheduleBuilder sinpleSchedual = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(jobSimple.getRepeatTimes());
					if(jobSimple.getIntervalseconds()!=null){
						sinpleSchedual.withIntervalInSeconds(jobSimple.getIntervalseconds());
					}
					if(jobSimple.getStartTime()!=null){
						triggerBuilder.startAt(jobSimple.getStartTime());
					}
					trigger = triggerBuilder.withSchedule(sinpleSchedual).build();
				}else if(job instanceof ScheduleJobCron){
					ScheduleJobCron jobCron=(ScheduleJobCron)job;
					String cronExpression = jobCron.getCronExpression();
					if(cronExpression==null){
						throw new SystemException("任务表达式不能为空！");
					}
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
					trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
				}
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				JobDetail jobDetail = scheduler.getJobDetail(getJobKey(job));
				ScheduleJob jobOld = (ScheduleJob)jobDetail.getJobDataMap().get("scheduleJob");
				jobOld.setPara(job.getPara());
				/* Trigger已存在，那么更新相应的定时设置 */
				if(job instanceof ScheduleJobSimple){
					ScheduleJobSimple scheduleJobSimple=(ScheduleJobSimple)job;
					SimpleScheduleBuilder simpleSchedual = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(scheduleJobSimple.getRepeatTimes());
					if(scheduleJobSimple.getIntervalseconds()!=null){
						simpleSchedual.withIntervalInSeconds(scheduleJobSimple.getIntervalseconds());
					}
					/* 重新构建trigger */
					TriggerBuilder<SimpleTrigger> triggerBuilder = ((SimpleTrigger)trigger).getTriggerBuilder();
					if(scheduleJobSimple.getStartTime()!=null){
						triggerBuilder.startAt(scheduleJobSimple.getStartTime());
					}
					trigger = triggerBuilder.withSchedule(simpleSchedual).build();
				}else if(job instanceof ScheduleJobCron){
					ScheduleJobCron scheduleJobCron=(ScheduleJobCron)job;
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobCron.getCronExpression());
					/* 按新的cronExpression表达式重新构建trigger */
					trigger = ((CronTrigger)trigger).getTriggerBuilder().withSchedule(scheduleBuilder).build();
				}
				/* 按新的trigger重新设置job执行 */
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			logger.error(job.toString());
			logger.error("任务初始化失败", e);
		}
	}
	/**
	 * 添加一个任务，如果任务已经存在则更新该任务
	 *
	 * @param job
	 * @throws SchedulerException
	 */
	public void addAndResetScheduleJob(ScheduleJob job) {
		JobKey jobKey = getJobKey(job);
		try {
			scheduler.deleteJob(jobKey);

			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(jobKey).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup());
			if (job instanceof ScheduleJobSimple) {
				ScheduleJobSimple jobSimple = (ScheduleJobSimple) job;
				SimpleScheduleBuilder sinpleSchedual = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(jobSimple.getRepeatTimes());
				if (jobSimple.getIntervalseconds() != null) {
					sinpleSchedual.withIntervalInSeconds(jobSimple.getIntervalseconds());
				}
				if (jobSimple.getStartTime() != null) {
					triggerBuilder.startAt(jobSimple.getStartTime());
				}
				scheduler.scheduleJob(jobDetail, triggerBuilder.withSchedule(sinpleSchedual).build());
			} else if (job instanceof ScheduleJobCron) {
				ScheduleJobCron jobCron = (ScheduleJobCron) job;
				String cronExpression = jobCron.getCronExpression();
				if (cronExpression == null) {
					throw new SystemException("任务表达式不能为空！");
				}
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
				scheduler.scheduleJob(jobDetail, triggerBuilder.withSchedule(scheduleBuilder).build());
			}
		} catch (SchedulerException e) {
			logger.error(job.toString());
			logger.error("任务初始化失败", e);
		}
	}
	
	private JobKey getJobKey(ScheduleJob scheduleJob){
		if(scheduleJob==null || scheduleJob.getJobName()==null){
			throw new SystemException("任务不能为空");
		}
		return JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}

	/**
	 * 取得已经设定的job
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public ScheduleJob getScheduledJob(ScheduleJob scheduleJob) {
		try {
			final JobDetail jobDetail = scheduler.getJobDetail(getJobKey(scheduleJob));
			if(jobDetail==null){
				return null;
			}
			return (ScheduleJob)jobDetail.getJobDataMap().get("scheduleJob");
		} catch (SchedulerException e) {
			logger.error("Task pause failed.", e);
		}
		return null;
	}
	/**
	 * 暂停一个job
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) {
		try {
			scheduler.pauseJob(getJobKey(scheduleJob));
		} catch (SchedulerException e) {
			logger.error("Task pause failed.", e);
		}
	}

	/**
	 * 恢复一个job
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) {
		try {
			scheduler.resumeJob(getJobKey(scheduleJob));
		} catch (SchedulerException e) {
			logger.error(scheduleJob.toString());
			logger.error("Task resume failed.", e);
		}
	}

	/**
	 * 删除一个job
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) {
		try {
			scheduler.deleteJob(getJobKey(scheduleJob));
		} catch (SchedulerException e) {
			logger.error(scheduleJob.toString());
			logger.error("Task delete failed.", e);
		}
	}

	/**
	 * 立即执行job
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runJobNow(ScheduleJob scheduleJob) {
		try {
			scheduler.triggerJob(getJobKey(scheduleJob));
		} catch (SchedulerException e) {
			logger.error(scheduleJob.toString());
			logger.error("Task run failed.", e);
		}
	}
	
	/**
	 * 判断表达式是否可用 @param cron @return @throws
	 */
	public boolean checkCron(String cron) {
		try {
			CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			logger.equals(e.getMessage());
			return (false);
		}
		return (true);
	}
}