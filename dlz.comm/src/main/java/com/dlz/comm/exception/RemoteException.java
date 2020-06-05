package com.dlz.comm.exception;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * BaseException for SDK
 */
public class RemoteException extends BaseException {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 7001;

	protected RemoteException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	private RemoteException(String message, Throwable cause) {
		super(DEFUALT_ERROR_CODE, message, cause);
	}

	private RemoteException(String message) {
		super(DEFUALT_ERROR_CODE, message);
	}
	
	public static void buildException(String message,Throwable cause){
		if(cause!=null && cause instanceof UnknownHostException || cause instanceof IOException || cause instanceof SocketException) {
			throw new RemoteException(7000,message, cause);
		}else if(cause!=null && cause instanceof IOException){
			throw new RemoteException(7002,message, cause);
		}
		throw new RemoteException(message, cause);
	}
}
