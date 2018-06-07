package com.dlz.framework.db.mySequence.bean;

import java.util.Date;

import com.dlz.framework.util.StringUtils;

public class SequenceBo {
	/**
	 * seq名
	 */
	private String seqName;
	/**
	 * 当前值
	 */
	private Long seqValue;
	/**
	 * 最小值
	 */
	private Long minValue;
	/**
	 * 最大值
	 */
	private Long maxValue;
	/**
	 * 每次取值的数量
	 */
	private Long step;
	/** */
	private Date tmCreate;
	/** */
	private Date tmSmp;

	public boolean validate() {
		// 一些简单的校验。如当前值必须在最大最小值之间。step值不能大于max与min的差
		if (StringUtils.isEmpty(seqName) || minValue < 0 || maxValue <= 0 || step <= 0 || minValue >= maxValue || maxValue - minValue <= step
				|| seqValue < minValue || seqValue > maxValue) {
			return false;
		}
		return true;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public Long getSeqValue() {
		return seqValue;
	}

	public void setSeqValue(Long seqValue) {
		this.seqValue = seqValue;
	}

	public Long getMinValue() {
		return minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public Date getTmCreate() {
		return tmCreate;
	}

	public void setTmCreate(Date tmCreate) {
		this.tmCreate = tmCreate;
	}

	public Date getTmSmp() {
		return tmSmp;
	}

	public void setTmSmp(Date tmSmp) {
		this.tmSmp = tmSmp;
	}
	
	
}