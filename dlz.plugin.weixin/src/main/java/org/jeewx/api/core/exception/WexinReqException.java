package org.jeewx.api.core.exception;

/**
 * 常规异常信息
 * @author sfli.sir
 *
 */
public class WexinReqException extends Exception {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static final long serialVersionUID = 1L;

	public WexinReqException(String message){
		super(message);
	}
	
	public WexinReqException(Throwable cause)
	{
		super(cause);
	}
	
	public WexinReqException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
