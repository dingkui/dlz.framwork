package com.dlz.app.sys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.comm.util.DateUtil;
import com.dlz.comm.util.config.ConfUtil;

public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 保存文件，返回文件路径
	 * 
	 * @param file
	 * @param subFolder
	 * @return
	 */
	public static String upload(File file, String subFolder) {
		String folderPath = null;
		try {
			if (file == null) {
				throw new IllegalArgumentException("file or fileName object is null");
			}
			if (subFolder == null || "".equals(subFolder)) {
				throw new IllegalArgumentException("subFolder is null");
			}
			String fileName = file.getName();
			String fileExt = getFileExt(fileName);
			fileName = DateUtil.getDateStr(new Date(), "mmssSSS") + getRandStr(3) + "." + fileExt;
			folderPath = "attachment/" + subFolder + "/" + getTimePath() + fileName;
			
			File destFile = new File(folderPath2Filepath(folderPath));
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			// 保存文件
			fileChannelCopy(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return folderPath;
	}

	/**
	 * 取得文件绝对网络路径
	 */
	public static String folderPath2Href(String folderPath) {
		return ConfUtil.getConfig("nginxServer") + "/" + folderPath;
	}
	/**
	 * 取得文件绝对存储路径
	 */
	public static String folderPath2Filepath(String folderPath) {
		return ConfUtil.getConfig("nginxPath") + "/" + folderPath;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String filePath, String fileName) {
		File file = new File(filePath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				log.info("删除文件：" + fileName + "---成功!");
				return true;
			} else {
				log.error("删除文件：" + fileName + "---失败!");
				return false;
			}
		} else {
			log.info("删除文件失败," + fileName + "---文件不存在!");
			return false;
		}
	}

	private static String getTimePath() {
		Calendar now = Calendar.getInstance();
		int year = now.get(1);
		int month = now.get(2) + 1;
		int date = now.get(5);
		int minute = now.get(11);
		String filePath = "";
		if (year != 0) {
			filePath = filePath + year + "/";
		}
		if (month != 0) {
			filePath = filePath + month + "/";
		}
		if (date != 0) {
			filePath = filePath + date + "/";
		}
		if (minute != 0) {
			filePath = filePath + minute + "/";
		}
		return filePath;
	}

	/**
	 * 文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int index = fileName.lastIndexOf(46) + 1;
		return fileName.substring(index, fileName.length());
	}

	/**
	 * 获取随机数
	 * 
	 * @param n
	 * @return
	 */
	public static String getRandStr(int n) {
		Random random = new Random();
		String val = "", randStr = "";
		for (int i = 0; i < n; i++) {
			val = String.valueOf(random.nextInt(10));
			randStr += val;
		}
		return randStr;
	}

	/**
	 * 使用文件通道的方式复制文件
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 */
	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
