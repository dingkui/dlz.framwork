package com.dlz.plugin.test.db.dao.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DaoTest implements IDaoOperator {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DaoTest.class);
	@Override
	public long getSeq(String seqName) {
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
