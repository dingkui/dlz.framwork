package com.dlz.framework.db.mySequence;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.db.mySequence.bean.SequenceBo;
import com.dlz.framework.db.mySequence.bean.SequenceRange;

public class SequenceHolder {

	private final Lock lock = new ReentrantLock();

	/** seqName */
	private String seqName;

	/** sequenceDao */
	private SequenceDAO sequenceDAO;

	private SequenceBo sequenceBo;
	/** */
	private SequenceRange sequenceRange;
	/** 是否初始化 */
	private volatile boolean isInitialize = false;
	/** sequence初始化重试次数 */
	private int initRetryNum;
	/** sequence获取重试次数 */
	private int getRetryNum;

	/**
	 * <p>
	 * 构造方法
	 * </p>
	 * 
	 * @Title MysqlSequenceHolder
	 * @param sequenceDAO
	 * @param sequenceBo
	 * @param initRetryNum
	 *            初始化时，数据库更新失败后重试次数
	 * @param getRetryNum
	 *            获取nextVal时，数据库更新失败后重试次数
	 * @return
	 * @author coderzl
	 */
	public SequenceHolder(SequenceDAO sequenceDAO, SequenceBo sequenceBo, int initRetryNum, int getRetryNum) {
		this.sequenceDAO = sequenceDAO;
		this.sequenceBo = sequenceBo;
		this.initRetryNum = initRetryNum;
		this.getRetryNum = getRetryNum;
		if (sequenceBo != null)
			this.seqName = sequenceBo.getName();
	}

	/**
	 * <p>
	 * 初始化
	 * </p>
	 * 
	 * @Title init
	 * @param
	 * @return void
	 * @author coderzl
	 */
	public void init() {
		if (isInitialize == true) {
			throw new DbException("[" + seqName + "] the MysqlSequenceHolder has inited");
		}
		if (sequenceDAO == null) {
			throw new DbException("[" + seqName + "] the sequenceDao is null");
		}
		if (seqName == null || seqName.trim().length() == 0) {
			throw new DbException("[" + seqName + "] the sequenceName is null");
		}
		if (sequenceBo == null) {
			throw new DbException("[" + seqName + "] the sequenceBo is null");
		}
		if (!sequenceBo.validate()) {
			throw new DbException("[" + seqName + "] the sequenceBo validate fail. BO:" + sequenceBo);
		}
		// 初始化该sequence
		initSequenceRecord(sequenceBo);
		isInitialize = true;
	}

	/**
	 * <p>
	 * 获取下一个序列号
	 * </p>
	 * 
	 * @Title getNextVal
	 * @param
	 * @return long
	 * @author coderzl
	 */
	public long getNextVal() {
		if (isInitialize == false) {
			throw new DbException("[" + seqName + "] the MysqlSequenceHolder not inited");
		}
		if (sequenceRange == null) {
			throw new DbException("[" + seqName + "] the sequenceRange is null");
		}
		long curValue = sequenceRange.getAndIncrement();

		if (curValue == -1) {
			try {
				lock.lock();
				curValue = sequenceRange.getAndIncrement();
				if (curValue != -1) {
					return curValue;
				}
				sequenceRange = retryRange();
				curValue = sequenceRange.getAndIncrement();
			} finally {
				lock.unlock();
			}
		}
		return curValue;
	}

	/**
	 * <p>
	 * 初始化当前这条记录
	 * </p>
	 * 
	 * @Title initSequenceRecord
	 * @Description
	 * @param sequenceBo
	 * @return void
	 * @author coderzl
	 */
	private void initSequenceRecord(SequenceBo sequenceBo) {
		// 在限定次数内，乐观锁更新数据库记录
		for (int i = 1; i < initRetryNum; i++) {
			// 查询bo
			SequenceBo curBo = sequenceDAO.getSequence(sequenceBo.getName());
			if (curBo == null) {
				throw new DbException("[" + seqName + "] the current sequenceBo is null");
			}
			if (!curBo.validate()) {
				throw new DbException("[" + seqName + "] the current sequenceBo validate fail");
			}
			// 改变当前值
			long newValue = curBo.getVal() + curBo.getStep();
			// 检查当前值
			if (!checkCurrentValue(newValue, curBo)) {
				newValue = resetCurrentValue(curBo);
			}
			int result = sequenceDAO.updSequence(sequenceBo.getName(), curBo.getVal(), newValue);
			if (result > 0) {
				sequenceRange = new SequenceRange(curBo.getVal(), newValue - 1);
				curBo.setVal(newValue);
				this.sequenceBo = curBo;
				return;
			} else {
				continue;
			}
		}
		// 限定次数内，更新失败，抛出异常
		throw new DbException("[" + seqName + "] sequenceBo update error");
	}

	/**
	 * <p>
	 * 检查新值是否合法 新的当前值是否在最大最小值之间
	 * </p>
	 * 
	 * @param curValue
	 * @param curBo
	 * @return boolean
	 * @author coderzl
	 */
	private boolean checkCurrentValue(long curValue, SequenceBo curBo) {
		if (curValue > curBo.getMin() && curValue <= curBo.getMax()) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * 重置sequence当前值 ：当前sequence达到最大值时，重新从最小值开始
	 * </p>
	 * 
	 * @Title resetCurrentValue
	 * @param curBo
	 * @return long
	 * @author coderzl
	 */
	private long resetCurrentValue(SequenceBo curBo) {
		return curBo.getMin();
	}

	/**
	 * <p>
	 * 缓存区间使用完毕时，重新读取数据库记录，缓存新序列段
	 * </p>
	 * 
	 * @Title retryRange
	 * @param SequenceRange
	 * @author coderzl
	 */
	private SequenceRange retryRange() {
		for (int i = 1; i < getRetryNum; i++) {
			// 查询bo
			SequenceBo curBo = sequenceDAO.getSequence(sequenceBo.getName());
			if (curBo == null) {
				throw new DbException("[" + seqName + "] the current sequenceBo is null");
			}
			if (!curBo.validate()) {
				throw new DbException("[" + seqName + "] the current sequenceBo validate fail");
			}
			// 改变当前值
			long newValue = curBo.getVal() + curBo.getStep();
			// 检查当前值
			if (!checkCurrentValue(newValue, curBo)) {
				newValue = resetCurrentValue(curBo);
			}
			int result = sequenceDAO.updSequence(sequenceBo.getName(), curBo.getVal(), newValue);
			if (result > 0) {
				sequenceRange = new SequenceRange(curBo.getVal(), newValue - 1);
				curBo.setVal(newValue);
				this.sequenceBo = curBo;
				return sequenceRange;
			} else {
				continue;
			}
		}
		throw new DbException("[" + seqName + "] sequenceBo update error");

	}
}