package com.dlz.framework.db.cache;

import com.dlz.framework.cache.AbstractCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSetMetaData;
import java.util.HashMap;

/**
 * 数据库表字段缓存
 */
@Component
@Lazy
public class TableInfoCache extends AbstractCache<String, HashMap<String, Integer>> {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public TableInfoCache() {
		super(TableInfoCache.class.getSimpleName());
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
}
