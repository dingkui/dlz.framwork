package com.dlz.jf.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
@Service
public class DaoOperatorJfinal implements IDaoOperator {
	
	private static MyLogger logger = MyLogger.getLogger(DaoOperatorJfinal.class);
	
	@Override
	public long getSeq(String seqName) {
		return Db.queryBigDecimal("select SEQ_"+seqName+".nextval from dual").longValue();
	}
	
	@Override
	public long getSeqWithTime(String seqName) {
		return Db.queryBigDecimal("SELECT TO_NUMBER(TO_CHAR(sysdate, 'yymmdd')) * 100000000000 + SEQ_" + seqName + ".NEXTVAL FROM DUAL").longValue();
	}

	@Override
	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_page());
		logger.info("jFRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		List<Record> list=Db.find(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara());
		List<ResultMap> retList =new ArrayList<ResultMap>();
		for(Record m:list){
			ResultMap ret=new ResultMap();
			ret.putAll(m.getColumns());
			retList.add(ret);
		}
		return retList;
	}

	@Override
	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_cnt());
		logger.info("jFRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return Db.queryBigDecimal(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara()).intValue();
	}

	@Override
	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSqlRun());
		logger.info("jFRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return Db.update(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara());
	}

}
