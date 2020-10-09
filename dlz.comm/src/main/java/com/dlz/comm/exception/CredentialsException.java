package com.dlz.comm.exception;

/**
 * 认证异常
 */
public class CredentialsException extends BaseException {
    private static final long serialVersionUID = 4454410583070023L;

    private static int DEFUALT_ERROR_CODE = 2001;

    public CredentialsException(String message) {
        super(DEFUALT_ERROR_CODE, message, null);
    }
}
