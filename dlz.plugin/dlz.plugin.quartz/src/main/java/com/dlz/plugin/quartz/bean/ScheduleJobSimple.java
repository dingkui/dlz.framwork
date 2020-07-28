package com.dlz.plugin.quartz.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
@Getter
@Setter
public class ScheduleJobSimple extends ScheduleJob{

    /** 任务运行时间 */
    private Date startTime;
    
    /** 重复次数 */
    private int repeatTimes=0;
    
    /** 间隔秒数 */
    private Integer intervalseconds;
}