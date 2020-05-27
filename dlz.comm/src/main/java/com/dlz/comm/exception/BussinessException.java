package com.dlz.comm.exception;

import com.dlz.comm.util.ExceptionUtils;
import com.dlz.comm.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * BaseException for SDK
 */
public class BussinessException extends BaseException {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;
	private static int DEFUALT_ERROR_CODE = 3001;

	public BussinessException(String message, Throwable cause) {
		this(DEFUALT_ERROR_CODE, message, cause);
	}

	public BussinessException(String message) {
		this(DEFUALT_ERROR_CODE, message, null);
	}
	
	public BussinessException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * 断言这个 boolean 为 true
	 * <p>为 false 则抛出异常</p>
	 *
	 * @param expression boolean 值
	 * @param message    消息
	 */
	public static void isTrue(boolean expression, String message) {
		if (expression) {
			throw new BussinessException(message);
		}
	}
	/**
	 * 断言这个 object 不为 null
	 * <p>为 null 则抛异常</p>
	 *
	 * @param object  对象
	 * @param message 消息
	 */
	public static void notNull(Object object, String message) {
		isTrue(object == null, message);
	}

	/**
	 * 断言这个 value 不为 empty
	 * <p>为 empty 则抛异常</p>
	 *
	 * @param value   字符串
	 * @param message 消息
	 */
	public static void notEmpty(Object value, String message) {
		isTrue(StringUtils.isEmpty(value), message);
	}
}
