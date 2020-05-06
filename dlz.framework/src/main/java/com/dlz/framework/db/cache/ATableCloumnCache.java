package com.dlz.framework.db.cache;

import java.util.Map;

import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.DbCoverUtil;

public abstract class ATableCloumnCache extends AbstractCache<String, Map<String, Integer>> {
	public ATableCloumnCache(String cacheName) {
		super(cacheName);
		DbCoverUtil.setTableCloumnCache(this);
	}
	/**
	 * 取得字段对应的类型
	* @Title: converObj4Db 
	* @param @param tableName
	* @param @param clumnName
	* @param @param value
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	public abstract Object converObj4Db(String tableName,String clumnName,Object value);
	
	/**
	 * 判断字符串是否在表中存在
	* @Title: isClumnExists 
	* @param @param tableName
	* @param @param clumnName
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public abstract boolean isClumnExists(String tableName, String clumnName);
}
