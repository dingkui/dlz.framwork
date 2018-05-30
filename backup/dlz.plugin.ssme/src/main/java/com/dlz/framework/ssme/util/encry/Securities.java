package com.dlz.framework.ssme.util.encry;


public class Securities {

	/**
	 * Hash算法
	 */
	public static final String HASH_ALGORITHM = "SHA-1";

	/**
	 * Hash次数
	 */
	public static final int HASH_INTERATIONS = 1024;

	/**
	 * Salt长度
	 */
	public static final int SALT_SIZE = 8;

	/**
	 * 判断输入密码是否有效
	 */
	public static boolean validateHash(String input, String pwd, String salt) {
		if (input == null || pwd == null || salt == null) {
			return false;
		}
		String hash = null;
		try {
			hash = Encodes.encodeHex(Digests.sha1(input.getBytes(),
					Encodes.decodeHex(salt), Securities.HASH_INTERATIONS));
		} catch(Exception e) {
			return false;
		}
		return input.equals(hash);
	}

}
