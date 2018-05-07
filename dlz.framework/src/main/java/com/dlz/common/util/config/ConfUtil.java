package com.dlz.common.util.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.bean.JSONList;
import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.logger.MyLogger;


public class ConfUtil {
	private static MyLogger logger = MyLogger.getLogger(ConfUtil.class);
	
	private static Pattern paraPattern = Pattern.compile("\\$\\{([\\w\\.]+)\\}");
	/**
	 * 配置文件
	 */
	public static String CONFIG_FILE = "config.properties";

	/**
	 * Properties实例
	 */
	public static Properties props = new Properties();

	/**
	 * 构造方法
	 */
	private ConfUtil() {

	}

	static {
		loadProperty();
	}
	
	/**
	 * 从配置文件中读取所有的属性
	 */
	public static void loadProperty(String config) {
		CONFIG_FILE=config;
		loadProperty();
	}
	
	/**
	 * 从配置文件中读取所有的属性
	 */
	public static void loadProperty() {
		try {
			String[] configs=CONFIG_FILE.split(",");
			props.clear();
			for(String config:configs){
				URL resource = ConfUtil.class.getClassLoader().getResource(config);
				if(resource==null){
					continue;
				}
				InputStream file = new FileInputStream(resource.getFile());
				props.load(file);
			}
			String fromdbSetting=(String)props.get("fromdbSetting");
			if("false".equals(fromdbSetting)){
				return;
			}
			if(fromdbSetting==null){
				fromdbSetting="key.setting.getSettings";
			}
			ICommService baseSetService = (ICommService)SpringHolder.getBean(ICommService.class);
			ParaMap pm=new ParaMap(fromdbSetting);
			List<ResultMap> subjectList = baseSetService.getMapList(pm);
			for (ResultMap subject : subjectList) {
				String str = subject.getStr("id");
				if(!props.containsKey(str)){
					props.put(str, subject.getStr("text"));
				}
			}
		} catch (Exception e) {
			logger.error("读取配置文件出错", e);
		}
	}
	
	/**
	 * 根据属性名获得对应值，如果得不到值返回defaultValue
	 * 
	 * @param name
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return 属性对应的值
	 */
	public static String getConfig(String name, String defaultValue) {
		String ret = props.getProperty(name, defaultValue);
		if (ret == null) {
			return defaultValue;
		}
		ret=ret.trim();
		Matcher mat = paraPattern.matcher(ret);
		StringBuilder sb=null;
		int end=0;
	  	while(mat.find()){
	  		String group = mat.group(1);
	  		if(sb==null){
	  			sb=new StringBuilder(mat.start());
	  		}else{
	  			sb.append(mat.start());
	  		}
			sb.append(getConfig(group,""));
			end=mat.end();
	  	}
	  	if(end==0){
	  		return ret;
	  	}
	  	sb.append(ret.substring(end));
	  	ret=sb.toString();
	  	props.setProperty(name, ret);
		return ret;
	}

	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static String getConfig(String name) {
		return getConfig(name, null);
	}
	
	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static JSONMap getConfigMap(String name) {
		return new JSONMap(getConfig(name, null));
	}
	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static String getConfigStr(String name,String key) {
		return getConfigMap(name).getStr(key);
	}
	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static JSONList getConfigList(String name) {
		return new JSONList(getConfig(name, null));
	}
	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static String getConfigStr(String name,int index) {
		return getConfigList(name).getStr(index);
	}
}
