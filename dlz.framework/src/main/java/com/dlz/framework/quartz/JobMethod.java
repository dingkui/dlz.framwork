package com.dlz.framework.quartz;

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
import org.springframework.stereotype.Component;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.quartz.bean.ScheduleJob;
import com.dlz.framework.quartz.bean.ScheduleJobCron;
import com.dlz.framework.quartz.bean.ScheduleJobSimple;

/**
 * 提供Job任务相关的方法
 * 
 * @author xiaohe
 */
@Component
public class JobMethod {
	Scheduler scheduler;
	
	JobMethod(){
		SchedulerFactory sf = new StdSchedulerFactory();  
        try {
            scheduler = sf.getScheduler();  
            scheduler.start();
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
	}
	
	private static MyLogger log = MyLogger.getLogger(JobMethod.class);

	/**
	 * 添加一个任务，如果任务已经存在则更新该任务
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addAndResetScheduleJobOld(ScheduleJob scheduleJob) {
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		CronTrigger trigger;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();

				jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(((ScheduleJobCron)scheduleJob).getCronExpression());

				trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				/* Trigger已存在，那么更新相应的定时设置 */
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(((ScheduleJobCron)scheduleJob).getCronExpression());

				/* 按新的cronExpression表达式重新构建trigger */
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

				/* 按新的trigger重新设置job执行 */
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			log.error("Task init failed.", e);
		}
	}
	/**
	 * 添加一个任务，如果任务已经存在则更新该任务
	 *
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addAndResetScheduleJob(ScheduleJob scheduleJob) {
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		Trigger trigger;
		try {
			System.out.println(scheduler.checkExists(triggerKey));
			trigger = scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
				jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
				if(scheduleJob instanceof ScheduleJobSimple){
					ScheduleJobSimple scheduleJobSimple=(ScheduleJobSimple)scheduleJob;
					SimpleScheduleBuilder sinpleSchedual = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(scheduleJobSimple.getRepeatTimes());
					if(scheduleJobSimple.getIntervalseconds()!=null){
						sinpleSchedual.withIntervalInSeconds(scheduleJobSimple.getIntervalseconds());
					}
					trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startAt(scheduleJobSimple.getStartTime()).withSchedule(sinpleSchedual).build();
				}else if(scheduleJob instanceof ScheduleJobCron){
					ScheduleJobCron scheduleJobCron=(ScheduleJobCron)scheduleJob;
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobCron.getCronExpression());
					trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				}

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				/* Trigger已存在，那么更新相应的定时设置 */
				if(scheduleJob instanceof ScheduleJobSimple){
					ScheduleJobSimple scheduleJobSimple=(ScheduleJobSimple)scheduleJob;
					SimpleScheduleBuilder simpleSchedual = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(scheduleJobSimple.getRepeatTimes());
					if(scheduleJobSimple.getIntervalseconds()!=null){
						simpleSchedual.withIntervalInSeconds(scheduleJobSimple.getIntervalseconds());
					}
					/* 重新构建trigger */
					trigger = ((SimpleTrigger)trigger).getTriggerBuilder().startAt(scheduleJobSimple.getStartTime()).withIdentity(triggerKey).withSchedule(simpleSchedual).build();
				}else if(scheduleJob instanceof ScheduleJobCron){
					ScheduleJobCron scheduleJobCron=(ScheduleJobCron)scheduleJob;
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobCron.getCronExpression());

					/* 按新的cronExpression表达式重新构建trigger */
					trigger = ((CronTrigger)trigger).getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				}
				/* 按新的trigger重新设置job执行 */
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			log.error("Task init failed.", e);
		}
	}
	
	private JobKey getJobKey(ScheduleJob scheduleJob){
		return JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
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
			log.error("Task pause failed.", e);
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
			log.error("Task resume failed.", e);
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
			log.error("Task delete failed.", e);
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
			log.error("Task run failed.", e);
		}
	}
	
	/**
	 * 判断表达式是否可用 @param cron @return @throws
	 */
	public static boolean checkCron(String cron) {
		try {
			CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			return (false);
		}
		return (true);
	}
	
	public static void main(String[] args) {
		System.out.println(checkCron("0/5 * * * * ? *"));
	}
}