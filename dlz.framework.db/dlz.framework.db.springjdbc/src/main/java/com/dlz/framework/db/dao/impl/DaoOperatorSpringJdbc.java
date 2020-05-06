package com.dlz.framework.db.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.dao.IDaoOperator;
import com.dlz.framework.db.jdbc.JdbcUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.comm.util.JacksonUtil;
@Service
public class DaoOperatorSpringJdbc implements IDaoOperator {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DaoOperatorSpringJdbc.class);
	private JdbcTemplate jdbcTemplate;
	private ResultSetExtractor<List<ResultMap>> extractor;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		extractor=new ResultSetExtractor<List<ResultMap>>() {
			@Override
			public List<ResultMap> extractData(ResultSet rs) throws SQLException, DataAccessException {
				return JdbcUtil.buildResultMapList(rs);
			}
		};
	}

	@Override
	public long getSeq(String seqName) {
		seqName = seqName.toUpperCase();
		String sql = "select SEQ_" + seqName + ".nextval from dual";

		if ("postgresql".equalsIgnoreCase(DbInfo.getDbtype())) {
			sql = "select nextval('"+seqName+"')";
		} else {
			if (seqName.startsWith("S_") || seqName.startsWith("SEQ_")) {
				sql = "select " + seqName + ".nextval from dual";
			}
		}
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
	
	@Override
	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_page());
		if(logger.isInfoEnabled()){
			logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.query(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara(), extractor);
	}

	@Override
	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_cnt());
		if(logger.isInfoEnabled()){
			logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.queryForObject(paraMap.getSqlJdbc(), Integer.class, paraMap.getSqlJdbcPara());
	}

	@Override
	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSqlRun());
		if(logger.isInfoEnabled()){
			logger.info("JdbcRun:"+paraMap.getSqlJdbc()+"paras:"+JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.update(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara());
	}
}
