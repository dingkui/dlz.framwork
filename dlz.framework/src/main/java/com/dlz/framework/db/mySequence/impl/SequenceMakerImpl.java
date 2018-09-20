package com.dlz.framework.db.mySequence.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.mySequence.ISequenceMaker;
import com.dlz.framework.db.mySequence.SequenceDeal;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;

public class SequenceMakerImpl implements ISequenceMaker {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
//	@Autowired
//	private SequenceFactory sequenceFactory;
//	
//	@PostConstruct
//	private void init() {
//		SpringHolder.getBean(IDaoOperator.class).setSequenceMaker(this);
//		sequenceFactory.initAll();
//	}
//
//	/**
//	 * <p>
//	 * 获取指定sequence的序列号
//	 * </p>
//	 *
//	 * @param seqName
//	 *            sequence名
//	 * @return String 序列号
//	 * @author coderzl
//	 */
//	@Override
//	public long nextVal(String seqName) {
//		return sequenceFactory.getNextVal(seqName);
//	}
	
	@Autowired
	private ICommService commServiceImpl;
	SequenceDeal sequenceDeal;
	
	@PostConstruct
	private void init() {
		SpringHolder.getBean(IDaoOperator.class).setSequenceMaker(this);
		sequenceDeal = SequenceDeal.getSequenceDeal(commServiceImpl);
	}

	/**
	 * 获取指定sequence的序列号
	 * @param seqName sequence名
	 */
	@Override
	public long nextVal(String seqName) {
		return sequenceDeal.nextVal(seqName);
	}
}