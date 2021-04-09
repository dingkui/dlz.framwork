package com.dlz.framework.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.enums.DbTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.dlz.framework.db.convertor.rowMapper.MySqlColumnMapRowMapper;
import com.dlz.framework.db.convertor.rowMapper.OracleColumnMapRowMapper;


/**
 * 覆写jdbctemlate ，使用LowerCaseColumnMapRowMapper
 * @author kingapex
 * 2010-6-13上午11:05:32
 * 
 * 2018-10-17 dk 覆盖query和execute，去掉过多的sql debug日志,添加异常时的sql日志
 */
@Component
@Lazy
@Slf4j
public class MyJdbcTemplate extends JdbcTemplate {
	@Override
	protected RowMapper getColumnMapRowMapper() {
		if(DbInfo.getDbtype()== DbTypeEnum.ORACLE){
			return new OracleColumnMapRowMapper();
		}else if(DbInfo.getDbtype()== DbTypeEnum.MYSQL||DbInfo.getDbtype()== DbTypeEnum.POSTGRESQL){
			return new MySqlColumnMapRowMapper();
		}else{
			return new ColumnMapRowMapper();
		}
	}
	
	@Override
	@Nullable
	public <T> T query(
			PreparedStatementCreator psc, @Nullable final PreparedStatementSetter pss, final ResultSetExtractor<T> rse)
			throws DataAccessException {
		Assert.notNull(rse, "ResultSetExtractor must not be null");

		return execute(psc, new PreparedStatementCallback<T>() {
			@Override
			@Nullable
			public T doInPreparedStatement(PreparedStatement ps) throws SQLException {
				ResultSet rs = null;
				try {
					if (pss != null) {
						pss.setValues(ps);
					}
					rs = ps.executeQuery();
					return rse.extractData(rs);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
					if (pss instanceof ParameterDisposer) {
						((ParameterDisposer) pss).cleanupParameters();
					}
				}
			}
		});
	}
	
	private static String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		}
		else {
			return null;
		}
	}
	
	@Override
	@Nullable
	public <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action)
			throws DataAccessException {
		Assert.notNull(psc, "PreparedStatementCreator must not be null");
		Assert.notNull(action, "Callback object must not be null");

		Connection con = DataSourceUtils.getConnection(obtainDataSource());
		PreparedStatement ps = null;
		try {
			ps = psc.createPreparedStatement(con);
			applyStatementSettings(ps);
			T result = action.doInPreparedStatement(ps);
			handleWarnings(ps);
			return result;
		}
		catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool deadlock
			// in the case when the exception translator hasn't been initialized yet.
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			String sql = getSql(psc);
			JdbcUtils.closeStatement(ps);
			ps = null;
			DataSourceUtils.releaseConnection(con, getDataSource());
			con = null;
			log.error("Executing prepared SQL statement error:" + (sql != null ? " [" + sql + "]" : ""));
			throw translateException("PreparedStatementCallback", sql, ex);
		}
		finally {
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			JdbcUtils.closeStatement(ps);
			DataSourceUtils.releaseConnection(con, getDataSource());
		}
	}
}
