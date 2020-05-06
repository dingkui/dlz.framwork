package com.dlz.framework.db.exception;

import java.sql.SQLRecoverableException;

import com.dlz.framework.exception.BaseException;

/**
 * 数据库操作异常
 * 
 * @author dingkui
 *
 */
public class DbException extends BaseException {
	private static final long serialVersionUID = 1L;



	private static int ERROR_CODE = 1000;
	private static int DEFUALT_ERROR_CODE = 1003;

	public DbException(String message,int errorCode, Throwable cause) {
		super(errorCode, message, cause);
	}
	public DbException(String message,int errorCode) {
		super(errorCode, message, null);
	}
	public DbException(String message,Throwable cause) {
		super(DEFUALT_ERROR_CODE, cause.getMessage(), null);
	}
	public static DbException buildDbException(String message, Throwable cause) {
		if (cause instanceof SQLRecoverableException) {
			return new DbException(message,ERROR_CODE,  cause);
		}
		return new DbException(message,DEFUALT_ERROR_CODE, cause);
	}
}
