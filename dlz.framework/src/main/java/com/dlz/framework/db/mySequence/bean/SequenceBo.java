package com.dlz.framework.db.mySequence.bean;

import java.util.Date;

import com.dlz.comm.util.StringUtils;

public class SequenceBo {

	/**
	 * seq名
	 */
	private String name;
	/**
	 * 当前值
	 */
	private Long val=1l;
	/**
	 * 最小值
	 */
	private Long min;
	/**
	 * 最大值
	 */
	private Long max;
	/**
	 * 每次取值的数量
	 */
	private Long step;
	/** */
	private Date ct;
	/** */
	private Date ut;

	public boolean validate() {
		// 一些简单的校验。如当前值必须在最大最小值之间。step值不能大于max与min的差
		if (StringUtils.isEmpty(name) || min < 0 || max <= 0 || step <= 0 || min >= max || max - min <= step
				|| val < min || val > max) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getVal() {
		return val;
	}

	public void setVal(Long val) {
		this.val = val;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public Date getCt() {
		return ct;
	}

	public void setCt(Date ct) {
		this.ct = ct;
	}

	public Date getUt() {
		return ut;
	}

	public void setUt(Date ut) {
		this.ut = ut;
	}
}