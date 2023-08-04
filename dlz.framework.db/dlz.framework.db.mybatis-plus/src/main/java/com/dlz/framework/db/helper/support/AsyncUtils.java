package com.dlz.framework.db.helper.support;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dlz.framework.util.system.Reflections;
import lombok.AllArgsConstructor;
import com.dlz.framework.db.helper.util.DbNameUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Field;
import java.util.Set;

@EnableAsync
@AllArgsConstructor
public class AsyncUtils {
	IDbOp dbOp;
	// 同步执行
	public void initTableSync(Class<?> clazz) {
		initTable(clazz);
	}

	// 异步线程池
	@Async("sqlThreadPool")
	public void initTable(Class<?> clazz) {
		TableName table = clazz.getAnnotation(TableName.class);
		if (table != null) {
			String tableName = DbNameUtil.getDbTableName(clazz);
			// 创建表
			dbOp.createTable(tableName,clazz);
			Set<String> columns = dbOp.getTableColumnNames(tableName);
			// 建立字段
			Field[] fields = Reflections.getFields(clazz);
			for (Field field : fields) {
				String columnName=DbNameUtil.getDbClumnName(field);
				// 创建字段
				if (columnName!=null && !columnName.equalsIgnoreCase("id") && !columns.contains(columnName)) {
					dbOp.createColumn(tableName,columnName,field);
				}
			}
		}
	}
}
