package com.dlz.framework.db.mySequence.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.framework.db.mySequence.ISequenceMaker;
import com.dlz.framework.db.mySequence.SequenceDeal;

/**
 * 框架中取得sequence的方法
 * 
 * @author dingkui
 *
 * 使用方法： 
 * 
 1:创建表
  CREATE TABLE `t_sequence` (
	 `name` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '序列名称',
	 `val` bigint(20) NOT NULL COMMENT '目前序列值',
	 `min` bigint(20) NOT NULL COMMENT '最小值',
	 `max` bigint(20) NOT NULL COMMENT '最大值',
	 `step` bigint(20) NOT NULL COMMENT '每次取值的数量',
	 `ct` datetime NOT NULL COMMENT '创建时间',
	 `ut` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	 PRIMARY KEY (`name`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流水号生成表';
  2: spring xml中添加 
  <bean id="sequenceMaker" class="com.dlz.framework.db.mySequence.impl.SequenceMakerImpl" lazy-init="true"/>
 *
 */
public class SequenceMakerImpl implements ISequenceMaker {

	
	@Autowired
	SequenceDeal sequenceDeal;
	
	/**
	 * 获取指定sequence的序列号
	 * @param seqName sequence名
	 */
	@Override
	public long nextVal(String seqName) {
		return sequenceDeal.nextVal(seqName);
	}
}