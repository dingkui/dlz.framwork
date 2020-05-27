package com.dlz.comm.exception;

/**
 * BaseException for SDK
 */
public class BaseException extends RuntimeException {
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5345825923487658213L;
    /**
     * 错误码 见Errors
     */
    private int code;
    /**
     * 错误码 对应的错误
     */
    private String info;
    /**
     * 错误码 见Errors
     */
    private String msg;

    public BaseException(int code, Throwable exception) {
        this(code, exception.getMessage(), exception);
    }

    public BaseException(int code, String message, Throwable cause) {
        this(code, message);
        if (cause != null) {
            this.addSuppressed(cause);
        }
    }

    public BaseException(int code, String message) {
        super(code + ":"  + (message == null ? "" : "[" + message + "]"));
        this.code = code;
        this.info = ExceptionErrors.getInfo(code);
        this.msg = message;
    }

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

	public String getMsg() {
		return msg;
	}

    public boolean is(int code){
        return this.code == code;
    }

}
