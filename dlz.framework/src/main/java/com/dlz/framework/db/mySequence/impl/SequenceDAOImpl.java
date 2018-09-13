//package com.dlz.framework.db.mySequence.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.dlz.framework.db.mySequence.SequenceDAO;
//import com.dlz.framework.db.mySequence.bean.SequenceBo;
//import com.dlz.framework.db.service.ICommService;
//
//public class SequenceDAOImpl implements SequenceDAO {
//	@Autowired
//	ICommService commServiceImpl;
//	/**
//	 * 
//	 * 
//	 CREATE TABLE `t_sequence` (
//	 `name` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '序列名称',
//	 `val` bigint(20) NOT NULL COMMENT '目前序列值',
//	 `min` bigint(20) NOT NULL COMMENT '最小值',
//	 `max` bigint(20) NOT NULL COMMENT '最大值',
//	 `step` bigint(20) NOT NULL COMMENT '每次取值的数量',
//	 `ct` datetime NOT NULL COMMENT '创建时间',
//	 `ut` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
//	 PRIMARY KEY (`name`)
//	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流水号生成表';
//	 * 
//	 */
//
//	@Override
//	public int updSequence(String seqName, long oldValue, long newValue) {
//		return commServiceImpl.excuteSql("update t_sequence set val=? where name=?",newValue,seqName);
//	}
//
//	@Override
//	public SequenceBo getSequence(String seqName) {
//		return commServiceImpl.getBean("select * from t_sequence where name=?", SequenceBo.class, seqName);
//	}
//
//	@Override
//	public List<SequenceBo> getAll() {
//		return commServiceImpl.getBeanList("select * from t_sequence", SequenceBo.class);
//	}
//
//}