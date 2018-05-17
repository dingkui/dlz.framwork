package com.dlz.quartz;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.quartz.JobMethod;
import com.dlz.framework.quartz.bean.ScheduleJobCron;
import com.dlz.framework.quartz.bean.ScheduleJobSimple;

public class QuartzTest {
	public static JobMethod jobMethod;
	@BeforeClass
	public static void before() throws Exception {
		SpringHolder.init();
		QuartzTest q=SpringHolder.registerBean(QuartzTest.class);
		jobMethod = SpringHolder.getBean(JobMethod.class);
	}
	
	
	@Test
	public void add1() throws InterruptedException{
		ScheduleJobCron scheduleJob=new ScheduleJobCron();
		scheduleJob.setJobName("test1");
		scheduleJob.setCronExpression("0/1 * * * * ? *");
		jobMethod.addAndResetScheduleJob(scheduleJob);
//		cs.runJobNow(scheduleJob);
		Thread.sleep(5000);
		scheduleJob.setCronExpression("0/3 * * * * ? *");
		jobMethod.addAndResetScheduleJob(scheduleJob);
		System.out.println("123");
		Thread.sleep(10000);
		System.out.println("456");
	}
	
	@Test
	public void add2() throws InterruptedException{
		ScheduleJobSimple scheduleJob=new ScheduleJobSimple();
		scheduleJob.setJobName("test2");
		scheduleJob.setStartTime(new Date(new Date().getTime()+5000));
		scheduleJob.setRepeatTimes(30);
		scheduleJob.setIntervalseconds(1);
		jobMethod.addAndResetScheduleJob(scheduleJob);
		System.out.println("123");
		Thread.sleep(10000);
		System.out.println("333");
		scheduleJob.setStartTime(new Date(new Date().getTime()+5000));
		scheduleJob.setRepeatTimes(5);
		scheduleJob.setIntervalseconds(2);
		jobMethod.addAndResetScheduleJob(scheduleJob);
		Thread.sleep(20000);
		System.out.println("456");
	}
	
	
	@Test
	public void add3() throws InterruptedException{
		ScheduleJobSimple scheduleJob=new ScheduleJobSimple();
		scheduleJob.setJobName("dealGroupBuy");
		scheduleJob.setExecuteMethod("deal");
		scheduleJob.setPara(new JSONMap().add("id", 123));
		scheduleJob.setStartTime(new Date(new Date().getTime()+5000));
		jobMethod.addAndResetScheduleJob(scheduleJob);
	}

}
