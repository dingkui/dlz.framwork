package com.dlz.framework.db.template;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.dlz.framework.db.DbInfo;


/**
 * 覆写jdbctemlate ，使用LowerCaseColumnMapRowMapper
 * @author kingapex
 * 2010-6-13上午11:05:32
 */
@Component
public class LowerCaseJdbcTemplate extends JdbcTemplate {
	@Override
	protected RowMapper getColumnMapRowMapper() {
		if("ORACLE".equals(DbInfo.getDbtype())){
			return new OracleColumnMapRowMapper();
		}else if("MYSQL".equals(DbInfo.getDbtype())){
			return new MySqlColumnMapRowMapper();
		}else{
			return new ColumnMapRowMapper();
		}
	}

}
