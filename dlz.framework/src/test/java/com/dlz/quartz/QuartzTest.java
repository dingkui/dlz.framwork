package com.dlz.quartz;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.quartz.JobMethod;
import com.dlz.framework.quartz.bean.ScheduleJobCron;
import com.dlz.framework.quartz.bean.ScheduleJobSimple;

public class QuartzTest {
	public static JobMethod jobMethod;
	@BeforeClass
	public static void before() throws Exception {
		SpringHolder.init();
		jobMethod = SpringHolder.getBean(JobMethod.class);
	}
	
	@Test
	public void init(){
		System.out.println(SpringHolder.getBean(Test2.class));
		System.out.println(SpringHolder.getBean(Test2.class));
	}
	
	@Test
	public void add1() throws InterruptedException{
		ScheduleJobCron scheduleJob=new ScheduleJobCron();
		scheduleJob.setJobName("test1");
		scheduleJob.setCronExpression("0/1 * * * * ? *");
		jobMethod.addAndResetScheduleJob(scheduleJob);
		Thread.sleep(5000);
		ScheduleJobCron scheduleJob2=new ScheduleJobCron();
		scheduleJob2.setJobName("test1");
		scheduleJob2.setPara(new JSONMap().add("aa", 123));
		scheduleJob2.setCronExpression("0/3 * * * * ? *");
		jobMethod.addAndResetScheduleJob(scheduleJob2);
		System.out.println("123");
		Thread.sleep(10000);
		System.out.println("456");
	}
	
	@Test
	public void add2() throws InterruptedException{
		ScheduleJobSimple scheduleJob=new ScheduleJobSimple();
		scheduleJob.setJobName("test1");
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
		scheduleJob.setJobName("test1");
		scheduleJob.setExecuteMethod("deal");
		scheduleJob.setPara(new JSONMap().add("id", 123));
		scheduleJob.setStartTime(new Date(new Date().getTime()+5000));
		jobMethod.addAndResetScheduleJob(scheduleJob);
	}
	
	
	@Test
	public void add4() throws InterruptedException{
		ScheduleJobSimple scheduleJob=new ScheduleJobSimple();
		scheduleJob.setJobName("dealGroupBuy");
		scheduleJob.setBeanClass(Test1.class.getName());
		scheduleJob.setPara(new JSONMap().add("id", 123));
		//开始时间不设置则为立即执行
//		scheduleJob.setStartTime(new Date(new Date().getTime()+1000));
		jobMethod.addAndResetScheduleJob(scheduleJob);
		
		Thread.sleep(20000);
		System.out.println("456");
	}
	@Test
	public void add5() throws InterruptedException{
		ScheduleJobSimple scheduleJob=new ScheduleJobSimple();
		scheduleJob.setJobName("dealGroupBuy");
		scheduleJob.setBeanClass(Test1.class.getName());
		scheduleJob.setPara(new JSONMap().add("id", 123));
		//开始时间不设置则为立即执行
//		scheduleJob.setStartTime(new Date(new Date().getTime()+1000));
		scheduleJob.setRepeatTimes(30);
		scheduleJob.setIntervalseconds(1);
		jobMethod.addAndResetScheduleJob(scheduleJob);
		System.out.println("123");
		Thread.sleep(5000);
		scheduleJob.setPara(new JSONMap().add("id", 345));
		scheduleJob.setStartTime(new Date(new Date().getTime()+5000));
		scheduleJob.setRepeatTimes(5);
		scheduleJob.setIntervalseconds(2);
		jobMethod.addAndResetScheduleJob(scheduleJob);
		Thread.sleep(20000);
		System.out.println("456");
	}

}
