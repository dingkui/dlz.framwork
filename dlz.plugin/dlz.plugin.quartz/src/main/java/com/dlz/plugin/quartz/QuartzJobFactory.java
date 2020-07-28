package com.dlz.plugin.quartz;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.util.system.Reflections;
import com.dlz.plugin.quartz.bean.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class QuartzJobFactory implements Job {
	public static final String JOB_KEY = "JOB_KEY";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(JOB_KEY);
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
			log.error(e.getMessage(), e);
		}
	}
}
