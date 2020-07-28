package com.dlz.plugin.quartz.bean;

import com.dlz.comm.json.JSONMap;
import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
@Getter
@Setter
public class ScheduleJob{

    /** 任务id */
    private int jobId;
    
    /** 任务名称 */
    private String jobName;
    
    /** 任务分组 */
    private String jobGroup;
    
    /** 任务状态 0禁用 1启用 2删除*/
    private String jobStatus;
    
    /** 任务执行类 (可选)
     * 如果未设置则取jobName对应的spring对象进行处理
     */
    private String beanClass;
    
    /** 任务执行方法  默认方法是run*/
    private String executeMethod="run";
    
    /** 任务执行方法 的参数*/
    private JSONMap para;
    
    /** 任务描述 */
    private String jobDesc;
}