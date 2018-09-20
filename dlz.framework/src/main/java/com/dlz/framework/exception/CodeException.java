package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class CodeException extends BaseException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3002;
	public enum CodeError{
		E3002("代码异常"),//业务异常
		E3002_1("ThreadHolder注入未执行"),//
	  ;
	  public String message;
		private CodeError(String message) {
			this.message = message;
		}
		protected String getMessage() {
			return message;
		}
	}
	CodeError codeErr=CodeError.E3002;
	public CodeError getCodeErr(){
		return codeErr;
	}
	public CodeException(String message) {
		this(message,null);
	}
	public CodeException(int err) {
		this(CodeError.valueOf("E3002_"+err).getMessage());
		this.codeErr=CodeError.valueOf("E3002_"+err);
	}
	public CodeException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}
}
