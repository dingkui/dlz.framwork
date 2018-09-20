package com.dlz.framework.db.exception;

/**
 * 数据库操作运行期异常
 * 
 * @author dk 2010-1-6下午06:16:47
 */
public class DBRuntimeException extends DbException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static final long serialVersionUID = -368646349014580765L;

	private static int DEFUALT_ERROR_CODE = 1001;

	public DBRuntimeException(String sql) {
		super(DEFUALT_ERROR_CODE, "数据库运行期异常,sql:" + sql, null);
	}

	public DBRuntimeException(Exception e, String sql) {
		super(DEFUALT_ERROR_CODE, "数据库运行期异常,sql:" + sql, e);
	}
}
