package com.dlz.framework.exception;

/**
 * BaseException for SDK
 */
public class RemoteException extends BaseException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	
	public RemoteException(String msg) {
		super(msg,2);
	}

	public RemoteException(String msg, Throwable exception) {
		super(msg,2,exception);
	}
}
