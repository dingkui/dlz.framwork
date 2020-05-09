package com.dlz.plugin.quartz.bean;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
public class ScheduleJobCron  extends ScheduleJob{

    /** 任务运行时间表达式 */
    private String cronExpression;
    
    /** 任务创建时间 */
    private String createTime;
    
    /** 任务更新时间 */
    private String updateTime;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}