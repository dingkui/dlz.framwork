package com.dlz.framework.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import com.dlz.framework.holder.SpringHolder;
import org.slf4j.Logger;
import com.dlz.framework.util.DateUtils;

/**
 * 定时调度器
 * @author dingkui
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TimerDispacher{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(TimerDispacher.class);
	private static Map<String,MyTimerTask> timmerSet = new HashMap<String,MyTimerTask>();
	private static Timer timer = new Timer();
	private static long EVREY=1000;
	
	public static <T> void addTimmer(String taskClass,T para,Date time) {
		long times=time.getTime()/EVREY+1;
		String key=String.valueOf(times);
		if(timmerSet.containsKey(key)){
			timmerSet.get(key).addPara(para);
		}else{
			try {
				MyTimerTask task =SpringHolder.getBean(taskClass);
				task.addPara(para);
				task.setKey(key);
				timmerSet.put(key,task);
				long delay=times*EVREY-new Date().getTime();
				timer.schedule(task,delay<0?0:delay);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);;
			}
		}
	}
	
	static void completeTask(String key) {
		timmerSet.remove(key);
	}
	
	public static void main(String[] args) {
//		String taskclass="com.dlz.framework.task.MyTimerTaskTest";
		String taskclass="myTimerTaskTest";
		Long dt1=DateUtils.toDate("2017-10-19 14:17:20", "yyyy-MM-dd HH:mm:ss").getTime();
		for(int i=0;i<36000;i++){
			addTimmer(taskclass, Long.valueOf(i), new Date(dt1+i*100));
		}
	}
}