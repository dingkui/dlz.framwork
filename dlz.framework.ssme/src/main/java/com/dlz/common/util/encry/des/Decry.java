package com.dlz.common.util.encry.des;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class Decry {
    public static void main(String[] args) throws Exception {
        // DES算法要求有一个可信任的随机数源
         SecureRandom sr = new SecureRandom();
        // 获得密匙数据
         FileInputStream fi = new FileInputStream(new File("key.txt"));
        byte rawKeyData[] = new byte[fi.available()];// = new byte[5];
         fi.read(rawKeyData);
         fi.close();
        // 从原始密匙数据创建一个DESKeySpec对象
         DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
         SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成解密操作
         Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
         cipher.init(Cipher.DECRYPT_MODE, key, sr);
        // 现在，获取数据并解密
         FileInputStream fi2 = new FileInputStream(new File("DigestPass.class"));
        byte encryptedData[] = new byte[fi2.available()];
         fi2.read(encryptedData);
         fi2.close();
        // 正式执行解密操作
//        byte decryptedData[] = cipher.doFinal(encryptedData);
        // 这时把数据还原成原有的类文件
        // FileOutputStream fo = new FileOutputStream(new
        // File("DigestPass.class"));
        // fo.write(decryptedData);
        // 用解密后的数据加载类并应用
     }
}
