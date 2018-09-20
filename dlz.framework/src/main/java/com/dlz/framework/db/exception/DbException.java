package com.dlz.framework.db.exception;

import java.sql.SQLRecoverableException;

import com.dlz.framework.exception.BaseException;


/**
 * 数据库操作异常
 * @author dingkui
 *
 */
public class DbException extends BaseException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private static int ERROR_CODE = 1000;
	private static int DEFUALT_ERROR_CODE = 1001;

	protected DbException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public DbException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}
	public DbException(String message) {
		super(DEFUALT_ERROR_CODE, message, null);
	}
	public DbException(Throwable cause) {
		super(DEFUALT_ERROR_CODE, cause.getMessage(), null);
	}
	
	public static DbException buildDbException(String message,Throwable cause){
		if(cause instanceof SQLRecoverableException){
			return new DbException(ERROR_CODE, message, cause);
		}
		return new DbException(message, cause);
	}
}
