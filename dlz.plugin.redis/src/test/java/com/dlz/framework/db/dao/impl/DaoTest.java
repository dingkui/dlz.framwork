package com.dlz.framework.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;

@Service
public class DaoTest implements IDaoOperator {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static MyLogger logger = MyLogger.getLogger(DaoTest.class);
	@Override
	public long getSeq(String seqName) {
		return 1l;
	}

	@Override
	public long getSeqWithTime(String seqName) {
		return 1l;
	}

	
	@Override
	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_page());
		logger.info("test:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		List<ResultMap> retList =new ArrayList<ResultMap>();
		return retList;
	}

	@Override
	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_cnt());
		logger.info("test:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return 1;
	}

	@Override
	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSqlRun());
		logger.info("test:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return 1;
	}

}
