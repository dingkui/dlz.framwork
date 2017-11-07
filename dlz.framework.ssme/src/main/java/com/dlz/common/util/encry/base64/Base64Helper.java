package com.dlz.common.util.encry.base64;

/** 
 * @author CuCuChen 
 * @version $Id$ 
 * @since 2004-5-18 
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Helper {
	private static Logger logger = LoggerFactory.getLogger(Base64Helper.class);

	// 从文本文件对象中读取内容并转换为字符数组
	public static char[] readChars(File file) {
		CharArrayWriter caw = new CharArrayWriter();
		try {
			Reader fr = new FileReader(file);
			Reader in = new BufferedReader(fr);
			int count = 0;
			char[] buf = new char[16384];
			while ((count = in.read(buf)) != -1) {
				if (count > 0)
					caw.write(buf, 0, count);
			}
			in.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return caw.toCharArray();
	}

	// 从字符串对象中读取内容并转换为字符数组
	public static char[] readChars(String string) {
		CharArrayWriter caw = new CharArrayWriter();
		try {
			Reader sr = new StringReader(string.trim());
			Reader in = new BufferedReader(sr);
			int count = 0;
			char[] buf = new char[16384];
			while ((count = in.read(buf)) != -1) {
				if (count > 0)
					caw.write(buf, 0, count);
			}
			in.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return caw.toCharArray();
	}

	// 从二进制文件对象中读取内容并转换为字节数组
	public static byte[] readBytes(File file) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			InputStream fis = new FileInputStream(file);
			InputStream is = new BufferedInputStream(fis);
			int count = 0;
			byte[] buf = new byte[16384];
			while ((count = is.read(buf)) != -1) {
				if (count > 0)
					baos.write(buf, 0, count);
			}
			is.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return baos.toByteArray();
	}

	// 写字节数组内容到二进制文件
	public static void writeBytes(File file, byte[] data) {
		try {
			OutputStream fos = new FileOutputStream(file);
			OutputStream os = new BufferedOutputStream(fos);
			os.write(data);
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	// 写字符数组内容到文本文件
	public static void writeChars(File file, char[] data) {
		try {
			Writer fos = new FileWriter(file);
			Writer os = new BufferedWriter(fos);
			os.write(data);
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
