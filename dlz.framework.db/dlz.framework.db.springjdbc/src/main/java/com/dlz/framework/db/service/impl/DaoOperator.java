package com.dlz.framework.db.service.impl;

import com.dlz.comm.util.JacksonUtil;
import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.convertor.JdbcUtil;
import com.dlz.framework.db.enums.DbTypeEnum;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.modal.items.SqlItem;
import com.dlz.framework.db.mySequence.ISequenceMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DaoOperator{
	@Autowired(required = false)
	ISequenceMaker sequenceMaker;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private ResultSetExtractor<List<ResultMap>> extractor = JdbcUtil::buildResultMapList;

	public long getSeq(String seqName) {
		if (sequenceMaker != null && sequenceMaker.isInited()) {
			return sequenceMaker.nextVal(seqName);
		}
		seqName = seqName.toUpperCase();
		String sql = "select SEQ_" + seqName + ".nextval from dual";
		if (DbInfo.getDbtype()== DbTypeEnum.POSTGRESQL) {
			sql = "select nextval('" + seqName + "')";
		} else {
			if (seqName.startsWith("S_") || seqName.startsWith("SEQ_")) {
				sql = "select " + seqName + ".nextval from dual";
			}
		}
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public List<ResultMap> getList(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap);
		SqlItem sqlItem = paraMap.getSqlItem();
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + sqlItem.getSqlJdbc() + " paras:" + JacksonUtil.getJson(sqlItem.getSqlJdbcPara()));
		}
		return jdbcTemplate.query(sqlItem.getSqlJdbc(), sqlItem.getSqlJdbcPara(), extractor);
	}

	public int getCnt(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap);
		SqlItem sqlItem = paraMap.getSqlItem();
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + sqlItem.getSqlJdbc() + " paras:" + JacksonUtil.getJson(sqlItem.getSqlJdbcPara()));
		}
		return jdbcTemplate.queryForObject(sqlItem.getSqlJdbc(), Integer.class, sqlItem.getSqlJdbcPara());
	}

	public int updateSql(BaseParaMap paraMap) {
		SqlUtil.dealParmToJdbc(paraMap);
		SqlItem sqlItem = paraMap.getSqlItem();
		if (log.isInfoEnabled()) {
			log.info("JdbcRun:" + sqlItem.getSqlJdbc() + " paras:" + JacksonUtil.getJson(sqlItem.getSqlJdbcPara()));
		}
		return jdbcTemplate.update(sqlItem.getSqlJdbc(), sqlItem.getSqlJdbcPara());
	}
}
