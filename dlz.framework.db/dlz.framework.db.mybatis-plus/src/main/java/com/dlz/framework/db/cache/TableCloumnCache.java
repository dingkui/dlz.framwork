package com.dlz.framework.db.cache;

import java.sql.Types;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.comm.util.ValUtil;

@Component
@Lazy
public class TableCloumnCache extends ATableCloumnCache {
	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	public TableCloumnCache() {
		super(TableCloumnCache.class.getSimpleName());
		dbOperator = new DbOperator() {
			protected Map<String, Integer> getFromDb(String tableName) {
//				tableName=tableName.toUpperCase();
//				// 查询表结构定义；返回表定义Map
//				String sql = "select * from " + tableName + " limit 0";
//				ResultSetExtractor<Map<String, Integer>> extractor = new ResultSetExtractor<Map<String, Integer>>() {
//					@Override
//					public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
//						Map<String, Integer> infos = new HashMap<>();
//						ResultSetMetaData rsmd = rs.getMetaData();
//						int columnCount = rsmd.getColumnCount();
//						for (int i = 1; i < columnCount + 1; i++) {
//							String columnLabel = SqlUtil.converClumnStr2Str(rsmd.getColumnLabel(i));
//							infos.put(columnLabel, rsmd.getColumnType(i));
//						}
//						return infos;
//					}
//				};
//				List<Map> r = sqlSessionTemplate.selectList(sql);
//				for(Map m : r){
//					
//				}
//				return null;
				return null;
				
			}
		};
	}

	@Override
	public Object converObj4Db(String tableName, String clumnName, Object value) {
//		Map<String, Integer> map = get(tableName.toUpperCase());
//		if (map != null) {
//			Integer dbClass = map.get(SqlUtil.converClumnStr2Str(clumnName));
//			if(dbClass==null){
//				return value;
//			}
//			return cover(dbClass, value);
//		}
		return value;
	}
	@Override
	public boolean isClumnExists(String tableName, String clumnName) {
//		Map<String, Integer> map = get(tableName.toUpperCase());
//		if (map == null) {
//			return false;
//		}
//		return map.containsKey(SqlUtil.converClumnStr2Str(clumnName));
		return true;
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
