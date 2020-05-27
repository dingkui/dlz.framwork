package com.dlz.framework.db.convertor.rowMapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import com.dlz.framework.db.modal.ResultMap;

/**
 * 本类覆写了spring 的RowMapper
 * @author dingkui
 */
public class ResultMapRowMapper implements RowMapper<ResultMap> {

	@Override
	public ResultMap  mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		ResultMap mapOfColValues = new ResultMap();
		for (int i = 1; i <= columnCount; i++) {
			String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
			Object obj = getColumnValue(rs, i);
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}
	
	
	/**
	 * Determine the key to use for the given column in the column Map.
	 * @param columnName the column name as returned by the ResultSet
	 * @return the column key to use
	 * @see java.sql.ResultSetMetaData#getColumnName
	 */
	protected String getColumnKey(String columnName) {
//		return columnName;
		return ConvertUtil.clumn2Str(columnName);
	}

	/**
	 * Retrieve a JDBC object value for the specified column.
	 * <p>The default implementation uses the {@code getObject} method.
	 * Additionally, this implementation includes a "hack" to get around Oracle
	 * returning a non standard object for their TIMESTAMP datatype.
	 * @param rs is the ResultSet holding the data
	 * @param index is the column index
	 * @return the Object returned
	 * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue
	 */
	protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index);
	}
}
