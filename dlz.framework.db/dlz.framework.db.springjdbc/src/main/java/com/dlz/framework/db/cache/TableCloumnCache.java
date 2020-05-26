package com.dlz.framework.db.cache;

import com.dlz.comm.util.ValUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
public class TableCloumnCache extends ATableCloumnCache {

	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;

	public TableCloumnCache() {
		super(TableCloumnCache.class.getSimpleName());
		dbOperator = new DbOperator() {
			protected HashMap<String, Integer> getFromDb(String tableName) {
				// 查询表结构定义；返回表定义Map
				String sql = "select * from " + tableName + " limit 0";
				ResultSetExtractor<HashMap<String, Integer>> extractor = rs -> {
					HashMap<String, Integer> infos = new HashMap<>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					for (int i = 1; i < columnCount + 1; i++) {
						String columnLabel = rsmd.getColumnLabel(i).toUpperCase();
						infos.put(columnLabel, rsmd.getColumnType(i));
					}
					return infos;
				};
				return jdbcTemplate.query(sql, extractor);
			}
		};
	}

	@Override
	public Object converObj4Db(String tableName, String clumnName, Object value) {
		Map<String, Integer> map = get(tableName);
		if (map != null) {
			Integer dbClass = map.get(clumnName.toUpperCase());
			if(dbClass==null){
				return value;
			}
			return cover(dbClass, value);
		}
		return value;
	}
	@Override
	public boolean isClumnExists(String tableName, String clumnName) {
		Map<String, Integer> map = get(tableName);
		if (map == null) {
			return false;
		}
		return map.containsKey(clumnName.toUpperCase());
	}

	private static Object cover(Integer dbClass, Object obj) {
		switch (dbClass) {
		case Types.SMALLINT:
		case Types.INTEGER:
			return ValUtil.getInt(obj);
		case Types.DECIMAL:
		case Types.BIGINT:
		case Types.NUMERIC:
			return ValUtil.getLong(obj);
		case Types.DOUBLE:
			return ValUtil.getDouble(obj);
		case Types.FLOAT:
			return ValUtil.getFloat(obj);
		case Types.CHAR:
		case Types.VARCHAR:
			return ValUtil.getStr(obj);
		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return ValUtil.getDate(obj);
		default:
			break;
		}
		return obj;
	}
}
