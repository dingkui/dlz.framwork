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
		E3002("代码异常"),//业务异常
		E4001("批处理异常"),//批处理异常
		E5002("参数异常"),//参数异常
		E6001("系统异常"),//参数异常
		E7000("远程服务器连接失败"),//远程服务器连接失败
		E7001("远程调用异常"),//远程调用异常
		E7002("远程调用数据读取异常"),//远程调用异常
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
	 * 错误码 见Errors
	 */
	private Errors error;
	
	/**
	 * 错误码 见Errors
	 */
	private String errorInfo;
	
	public BaseException(String message) {
		this(3001,message,null);
	}
	
	public BaseException(String message, Throwable exception) {
		this(3001,message,exception);
	}
	
	public BaseException(int errorCode, Throwable exception) {
		this(errorCode,exception.getMessage(),exception);
	}

	public BaseException(int errorCode,String message,Throwable cause) {
		this(Errors.valueOf("E"+errorCode),message);
		if(cause!=null){
			this.addSuppressed(cause);
		}
	}
	public BaseException(Errors error,String message) {
		super(error.toString()+":"+error.getMessage()+(message==null?"":"["+message+"]"));
		this.errorInfo=message;
		this.error=error;
	}
	public String getErrorMsg(){
		return error.getMessage();
	}
	public String getErrorCode(){
		return error.toString();
	}
	public String getErrorInfo(){
		return errorInfo;
	}
}
