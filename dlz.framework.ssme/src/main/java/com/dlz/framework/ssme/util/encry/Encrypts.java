package com.dlz.framework.ssme.util.encry;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.Charsets;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.logger.MyLogger;
public class Encrypts {
	private static MyLogger logger = MyLogger.getLogger(Encrypts.class);
	private static final Charset CHARSET_ENCODING = Charsets.UTF_8;

	private static final byte[] DEFAULT_KEY = "phonefu                 "
			.getBytes();

//	private static final byte[] DEFAULT_KEY = "D86D2ABC8C58D1496F5F6509D4C44500"
//			.getBytes();
	
	public enum EncryptType {
		// DES加密引擎(8位密钥)
		DES,

		// 3DES加密引擎(24位密钥)
		DESede,

		// AES加密引擎(16位密钥)
		AES
	}

	public static byte[] encrypt(byte[] src, byte[] key, EncryptType encryptType) {
		String type = encryptType.name();
		try {
			Cipher cipher = Cipher.getInstance(type);
			SecretKey securekey = new SecretKeySpec(key, type);
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			return cipher.doFinal(src);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static byte[] decrypt(byte[] src, byte[] key, EncryptType encryptType) {
		String type = encryptType.name();
		try {
			Cipher cipher = Cipher.getInstance(type);
			SecretKey securekey = new SecretKeySpec(key, type);
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			return cipher.doFinal(src);
		} catch (Exception e) {
			return null;
		}
	}

	public static String encrypt(String src, String key, EncryptType encryptType) {
		if (StringUtils.isEmpty(src) || StringUtils.isEmpty(key)
				|| encryptType == null) {
			return null;
		}
		return Encodes.encodeHex(encrypt(src.getBytes(CHARSET_ENCODING),
				key.getBytes(CHARSET_ENCODING), encryptType));
	}

	public static String decrypt(String src, String key, EncryptType encryptType) {
		if (StringUtils.isEmpty(src) || StringUtils.isEmpty(key)
				|| encryptType == null) {
			return null;
		}
		return new String(decrypt(Encodes.decodeHex(src),
				key.getBytes(CHARSET_ENCODING), encryptType));
	}

	public static String encrypt(String src, EncryptType encryptType) {
		return Encodes.encodeHex(encrypt(src.getBytes(CHARSET_ENCODING),
				DEFAULT_KEY, encryptType));
	}

	public static String decrypt(String src, EncryptType encryptType) {
		return new String(decrypt(Encodes.decodeHex(src), DEFAULT_KEY,
				encryptType));
	}

}
