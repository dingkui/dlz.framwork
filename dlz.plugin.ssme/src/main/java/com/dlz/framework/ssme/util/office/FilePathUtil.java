package com.dlz.framework.ssme.util.office;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.ssme.util.encry.base64.Base64;

public class FilePathUtil {
	private static MyLogger logger = MyLogger.getLogger(FilePathUtil.class);
    /**
     * 根据相对路径获取网络路径
     * @author  wangsl: 
     * @date 创建时间：2015-6-24 上午10:30:37 
     * @param fPath
     * @return
     */
	public static String getImgPath(String fPath){
		if(fPath!=null && fPath.toLowerCase().startsWith("http")){
			return fPath;
		}
		String showPath = ConfigUtil.getConfig("nginxServer");
		String srcImgPath = showPath+File.separator+fPath;
		//替换反斜杠为双反斜杠，去掉换行符
		srcImgPath = srcImgPath.replaceAll("\\\\", "/").replaceAll("\r|\n", "");
		return srcImgPath;
	}
	
	/**
	 * 将图片转换为base64编码供world导出
	 * @author  wangsl: 
	 * @date 创建时间：2015-5-15 下午3:11:19 
	 * @param path 数据库存储的图片路径
	 * @return
	 * @throws Exception
	 */
	public static String getImageStr(String path) throws Exception{
		//将数据库路径转换为服务器路径
		String imgFile = FilePathUtil.getImgPath(path);
		URL url = new URL(imgFile);
		URLConnection connection = url.openConnection();
		InputStream in = null;
		byte[] data = null;
		try {
			in = connection.getInputStream();
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return new String(Base64.encode(data));
	}
	
	/**
     * 根据相对路径获取绝对路径
     * @author  wangsl: 
     * @date 创建时间：2015-6-24 上午10:30:37 
     * @param fPath
     * @return
     */
	public static String getRealImgPath(String fPath){
		String showPath = ConfigUtil.getConfig("sys.img.upload.path");
		String srcImgPath = showPath+File.separator+fPath;
		String regexp = "\\\\"; 
		//替换反斜杠为双反斜杠，去掉换行符
		srcImgPath = srcImgPath.replace("\\", regexp).replaceAll("\r|\n", "");
		return srcImgPath;
	}
}
