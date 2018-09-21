package com.dlz.framework.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import org.slf4j.Logger;
import com.dlz.framework.quartz.bean.ScheduleJob;
import com.dlz.framework.util.system.Reflections;

public class QuartzJobFactory implements Job {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(JobMethod.class);
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
				Reflections.getMethod(obj, scheduleJob.getExecuteMethod()).invoke(obj);
			} else {
				Reflections.getMethod(obj, scheduleJob.getExecuteMethod(),JSONMap.class).invoke(obj, para);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
