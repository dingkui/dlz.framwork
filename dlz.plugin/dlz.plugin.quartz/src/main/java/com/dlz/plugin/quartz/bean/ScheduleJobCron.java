package com.dlz.plugin.quartz.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
@Getter
@Setter
public class ScheduleJobCron  extends ScheduleJob{

    /** 任务运行时间表达式 */
    private String cronExpression;

//    /** 任务创建时间 */
//    private String createTime;
//
//    /** 任务更新时间 */
//    private String updateTime;
}