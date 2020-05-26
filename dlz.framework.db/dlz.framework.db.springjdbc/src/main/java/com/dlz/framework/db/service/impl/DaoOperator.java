package com.dlz.framework.db.service.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.jdbc.JdbcUtil;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DaoOperator{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private ResultSetExtractor<List<ResultMap>> extractor = JdbcUtil::buildResultMapList;

	public long getSeq(String seqName) {
		seqName = seqName.toUpperCase();
		String sql = "select SEQ_" + seqName + ".nextval from dual";

		if ("postgresql".equalsIgnoreCase(DbInfo.getDbtype())) {
			sql = "select nextval('" + seqName + "')";
		} else {
			if (seqName.startsWith("S_") || seqName.startsWith("SEQ_")) {
				sql = "select " + seqName + ".nextval from dual";
			}
		}
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_page());
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + paraMap.getSqlJdbc() + " paras:" + JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.query(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara(), extractor);
	}

	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSql_cnt());
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + paraMap.getSqlJdbc() + " paras:" + JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.queryForObject(paraMap.getSqlJdbc(), Integer.class, paraMap.getSqlJdbcPara());
	}

	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap, paraMap.getSqlRun());
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + paraMap.getSqlJdbc() + " paras:" + JacksonUtil.getJson(paraMap.getSqlJdbcPara()));
		}
		return jdbcTemplate.update(paraMap.getSqlJdbc(), paraMap.getSqlJdbcPara());
	}
}
