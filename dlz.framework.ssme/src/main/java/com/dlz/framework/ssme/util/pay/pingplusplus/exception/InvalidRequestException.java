package com.dlz.framework.ssme.util.pay.pingplusplus.exception;

public class InvalidRequestException extends PingppException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static final long serialVersionUID = 1L;

	private final String param;

	public InvalidRequestException(String message, String param, Throwable e) {
		super(message, e);
		this.param = param;
	}

	public String getParam() {
		return param;
	}

}
