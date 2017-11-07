package com.dlz.common.util.encry.des;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryUtil {
	private static final String p = "key.txt";
	private static byte[] k;

	private synchronized static byte[] getK() throws Exception {
		if (k == null) {
			FileInputStream fi = null;
			try {
				File f = new File(p);
				if(!f.exists()){
					throw new Exception("key file is not absolute:" + f.getAbsolutePath());
				}
				fi = new FileInputStream(f); 
				k = new byte[fi.available()];
				fi.read(k);
				fi.close();
			} finally{
				if(fi!=null){
					fi.close();
				}
			}
		}
		return k;
	}

//	public static void main(String args[]) {
//		try {
//			String a = "xxx";
//			System.out.println(a);
//			byte[] b = encry(a);
//			System.out.println(a);
//			a = decry(b);
//			System.out.println(a);
//		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
//		}
//	}

	public static byte[] encry(String args) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(getK());
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 正式执行加密操作
		byte encryptedData[] = cipher.doFinal(args.getBytes());
		return encryptedData;
	}

	public static String decry(byte[] args) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(getK());
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// 正式执行解密操作
		byte decryptedData[] = cipher.doFinal(args);

		return new String(decryptedData);
	}
}
