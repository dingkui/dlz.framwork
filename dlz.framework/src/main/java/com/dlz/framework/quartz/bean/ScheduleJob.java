package com.dlz.framework.quartz.bean;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;

/**
 * 定时任务封装类
 * @author   xiaohe
 */
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

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	} 

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getExecuteMethod() {
		return executeMethod;
	}

	public void setExecuteMethod(String executeMethod) {
		this.executeMethod = executeMethod;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public JSONMap getPara() {
		return para;
	}

	public void setPara(JSONMap para) {
		this.para = para;
	}
	
	public String toString() {
		return JacksonUtil.getJson(this);
	}
}