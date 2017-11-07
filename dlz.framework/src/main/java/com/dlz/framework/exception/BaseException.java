package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class BaseException extends RuntimeException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	enum Errors{
	  	E9997("JSP内容异常"),//URL参数异常
	  	E9998("参数提交异常"),//URL参数异常
	  	E9999("JSP异常"),//JSP异常
		E1000("数据库连接异常"),//数据库操作异常
		E1001("数据库操作异常"),//数据库操作异常
		E1002("数据库操作异常:sql异常"),//数据库操作异常:sql异常
		E1003("数据库操作异常：参数异常"),//数据库操作异常：参数异常
		E2001("非法访问异常"),//非法访问异常
		E3001("业务异常"),//业务异常
		E4001("批处理异常"),//批处理异常
		E5002("参数异常"),//参数异常
		E6001("系统异常"),//参数异常
	  ;
	  public String message;
		private Errors(String message) {
			this.message = message;
		}
		protected String getMessage() {
			return message;
		}
	}
	/**
	 * 异常代码
	 * 0：逻辑跳出返回结果（非异常），
	 * 1:系统异常
	 * 2:远程调用异常
	 * 3:数据异常
	 */
	private int e_lv=0;

	public BaseException(String msg) {
		this(msg,0);
	}
	public BaseException(String msg,int e_lv) {
		super(msg);
		this.e_lv=e_lv;
	}

	public BaseException(String msg, Throwable exception) {
		this(msg,0,exception);
	}
	public BaseException(String msg,int e_lv, Throwable exception) {
		super(msg, exception);
		this.e_lv=e_lv;
	}
	
	public int getE_lv() {
		return e_lv;
	}
}
