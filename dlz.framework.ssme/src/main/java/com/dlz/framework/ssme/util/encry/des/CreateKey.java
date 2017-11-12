package com.dlz.framework.ssme.util.encry.des;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateKey {
	private static Logger logger = LoggerFactory.getLogger(CreateKey.class);
	@SuppressWarnings("resource")
	public void createKey(String keyName) throws Exception {
		// 创建一个可信任的随机数源，DES算法需要
		SecureRandom sr = new SecureRandom();
		// 用DES算法创建一个KeyGenerator对象
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		// 初始化此密钥生成器,使其具有确定的密钥长度
		kg.init(sr);
		// 生成密匙
		SecretKey key = kg.generateKey();
		// 获取密钥数据
		byte rawKeyData[] = key.getEncoded();
		// 将获取到密钥数据保存到文件中，待解密时使用
		FileOutputStream fo = new FileOutputStream(new File(keyName));
		fo.write(rawKeyData);
	}

	public static void main(String args[]) {
		try {
			File f = new File("key.txt");
			System.out.println(f.getAbsolutePath());
			new CreateKey().createKey("key.txt");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
