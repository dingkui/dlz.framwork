package com.dlz.framework.quartz.bean;

import java.util.Date;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
public class ScheduleJobSimple extends ScheduleJob
{
    /** 任务运行时间 */
    private Date startTime;
    
    /** 重复次数 */
    private int repeatTimes=0;
    
    /** 间隔秒数 */
    private Integer intervalseconds;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getRepeatTimes() {
		return repeatTimes;
	}

	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public Integer getIntervalseconds() {
		return intervalseconds;
	}

	public void setIntervalseconds(Integer intervalseconds) {
		this.intervalseconds = intervalseconds;
	}
}