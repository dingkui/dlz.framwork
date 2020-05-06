package com.dlz.framework.db.cache;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.modal.Page;
/**
 * 数据库查询结果缓存
 * @author dingkui
 *
 */
@SuppressWarnings("rawtypes")
@Component
@Lazy
public class DbOprationCache extends AbstractCache<String, Page> {

	public DbOprationCache() {
		super(DbOprationCache.class.getSimpleName());
	}
}
