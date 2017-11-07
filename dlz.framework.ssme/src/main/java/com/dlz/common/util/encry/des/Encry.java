package com.dlz.common.util.encry.des;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class Encry {
    public static void main(String[] args) throws Exception {
        // DES算法要求有一个可信任的随机数源
         SecureRandom sr = new SecureRandom();
        // 获得密匙数据
         FileInputStream fi = new FileInputStream(new File("key.txt"));
        byte rawKeyData[] = new byte[fi.available()];
         fi.read(rawKeyData);
         fi.close();
        // 从原始密匙数据创建DESKeySpec对象
         DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
         SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
        // Cipher对象实际完成加密操作
         Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
         cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        // 现在，获取要加密的文件数据
         FileInputStream fi2 = new FileInputStream(new File("DigestPass.class"));
        byte data[] = new byte[fi2.available()];
         fi2.read(data);
         fi2.close();
        // 正式执行加密操作
        byte encryptedData[] = cipher.doFinal(data);
        // 用加密后的数据覆盖原文件
         FileOutputStream fo = new FileOutputStream(new File("DigestPass.class"));
         fo.write(encryptedData);
         fo.close();
     }
}
