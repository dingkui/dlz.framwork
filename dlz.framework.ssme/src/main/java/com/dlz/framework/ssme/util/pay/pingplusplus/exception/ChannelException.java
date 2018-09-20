package com.dlz.framework.ssme.util.pay.pingplusplus.exception;

public class ChannelException extends PingppException {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static final long serialVersionUID = 1L;

	private final String param;

	public ChannelException(String message, String param, Throwable e) {
		super(message, e);
		this.param = param;
	}

	public String getParam() {
		return param;
	}

}
