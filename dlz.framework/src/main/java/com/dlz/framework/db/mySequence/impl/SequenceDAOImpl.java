package com.dlz.framework.db.mySequence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlz.framework.db.mySequence.SequenceDAO;
import com.dlz.framework.db.mySequence.bean.SequenceBo;
import com.dlz.framework.db.service.ICommService;

@Component
public class SequenceDAOImpl implements SequenceDAO {
	@Autowired
	ICommService commServiceImpl;
	/**
	 * 
	 * 
	 CREATE TABLE `t_pub_sequence` (
	 `SEQ_NAME` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '序列名称',
	 `SEQ_VALUE` bigint(20) NOT NULL COMMENT '目前序列值',
	 `MIN_VALUE` bigint(20) NOT NULL COMMENT '最小值',
	 `MAX_VALUE` bigint(20) NOT NULL COMMENT '最大值',
	 `STEP` bigint(20) NOT NULL COMMENT '每次取值的数量',
	 `TM_CREATE` datetime NOT NULL COMMENT '创建时间',
	 `TM_SMP` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	 PRIMARY KEY (`SEQ_NAME`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流水号生成表';
	 * 
	 */

	@Override
	public int updSequence(String seqName, long oldValue, long newValue) {
		return commServiceImpl.excuteSql("update t_pub_sequence set SEQ_VALUE=? where SEQ_NAME=?",newValue,seqName);
	}

	@Override
	public SequenceBo getSequence(String seqName) {
		return commServiceImpl.getBean("select * from t_pub_sequence where SEQ_VALUE=?", SequenceBo.class, seqName);
	}

	@Override
	public List<SequenceBo> getAll() {
		return commServiceImpl.getBeanList("select * from t_pub_sequence", SequenceBo.class);
	}

}