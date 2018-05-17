package com.dlz.framework.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.quartz.bean.ScheduleJob;

public class QuartzJobFactory implements Job {
	private static MyLogger logger = MyLogger.getLogger(JobMethod.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get("scheduleJob");
		Object obj = null;
		try {
			if (scheduleJob.getBeanClass() != null) {
				obj = SpringHolder.registerBean(Class.forName(scheduleJob.getBeanClass()));
			}else{
				obj = SpringHolder.getBean(scheduleJob.getJobName());
			}
			JSONMap para = scheduleJob.getPara();
			if (para == null) {
				obj.getClass().getMethod(scheduleJob.getExecuteMethod()).invoke(obj);
			} else {
				obj.getClass().getMethod(scheduleJob.getExecuteMethod(), JSONMap.class).invoke(obj, para);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
