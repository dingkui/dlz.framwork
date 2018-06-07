package com.dlz.framework.db.mySequence;

/**
 * <p>
 * </p>
 * 
 * @author coderzl
 * @Title MysqlSequence
 * @Description 基于mysql数据库实现的序列
 * @date 2017/6/6 23:03
 */
public interface ISequenceMaker {
	/**
	 * <p>
	 * 获取指定sequence的序列号
	 * </p>
	 * 
	 * @param seqName
	 *            sequence名
	 * @return String 序列号
	 */
	public long nextVal(String seqName);
}