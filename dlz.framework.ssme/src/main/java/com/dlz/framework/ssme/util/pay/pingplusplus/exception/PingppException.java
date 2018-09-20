package com.dlz.framework.ssme.util.pay.pingplusplus.exception;

public abstract class PingppException extends Exception {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	public PingppException(String message) {
		super(message, null);
	}

	public PingppException(String message, Throwable e) {
		super(message, e);
	}

	private static final long serialVersionUID = 1L;

}
