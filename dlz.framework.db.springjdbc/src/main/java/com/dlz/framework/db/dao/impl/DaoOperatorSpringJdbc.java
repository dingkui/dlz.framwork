package com.dlz.framework.db.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.jdbc.JdbcUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.JacksonUtil;
@Service
public class DaoOperatorSpringJdbc implements IDaoOperator {
	private static MyLogger logger = MyLogger.getLogger(DaoOperatorSpringJdbc.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public long getSeq(String seqName) {
		seqName=seqName.toUpperCase();
		String sql="select SEQ_"+seqName+".nextval from dual";
		if(seqName.startsWith("S_")||seqName.startsWith("SEQ_")){
			sql="select "+seqName+".nextval from dual";
		}
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
	
	@Override
	public long getSeqWithTime(String seqName) {
		seqName = seqName.toUpperCase();
		String sql = "SELECT TO_NUMBER(TO_CHAR(sysdate, 'yymmdd')) * 100000000000 + SEQ_" + seqName + ".NEXTVAL FROM DUAL";
		if (seqName.startsWith("S_") || seqName.startsWith("SEQ_")) {
			sql = "SELECT TO_NUMBER(TO_CHAR(sysdate, 'yymmdd')) * 100000000000 + " + seqName + ".NEXTVAL FROM DUAL";
		}
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
	
	private static ResultSetExtractor<List<ResultMap>> extractor = new ResultSetExtractor<List<ResultMap>>() {
		@Override
		public List<ResultMap> extractData(ResultSet rs) throws SQLException, DataAccessException {
			return JdbcUtil.buildResultMapList(rs);
		}
	};
	
	@Override
	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_page());
		logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return jdbcTemplate.query(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara(), extractor);
	}

	@Override
	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_cnt());
		logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return jdbcTemplate.queryForObject(paraMap.getSqlJdbc(), Integer.class, paraMap.getSqlJdbcPara());
	}

	@Override
	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSqlRun());
		logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		return jdbcTemplate.update(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara());
	}
}
