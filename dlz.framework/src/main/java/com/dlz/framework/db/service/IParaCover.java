package com.dlz.framework.db.service;

public interface IParaCover {
	public Object converObj4Db(String tableName,String clumnName,Object value);
	default void clear() {
		
	}
}
