package com.dlz.framework.db.exception;

import java.sql.SQLRecoverableException;

import com.dlz.framework.exception.BaseException;


/**
 * 数据库操作异常
 * @author dingkui
 *
 */
public class DbException extends BaseException {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(1001,msg,null);
	}

	public DbException(String msg, Throwable exception) {
		super(1001,msg, exception);
	}
	
	public static int getExceptionCode(Throwable cause){
		if(cause instanceof SQLRecoverableException){
			return 1000;
		}
		return 1001;
	}
}
