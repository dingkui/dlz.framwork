package com.dlz.framework.ssme.util.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.framework.ssme.db.model.BaseSet;
import com.dlz.framework.util.StringUtils;


public class ConfigUtil {
	private static Pattern paraPattern = Pattern.compile("\\$\\{([\\w\\.]+)\\}");
	/*
	 * 配置名称
	 */
	public static class Configs{
		/*
		 * 系统配置
		 */
		public static class Sys{
			/*
			 * 是否测试环境  0：否  1：是
			 */
			public static final String IS_TEST ="Sys.isTest";
			public static final String IS_TOMCAT ="Sys.isTomcat";
			public static final String imgUploadPath ="sys.img.upload.path";
			public static boolean isTest(){
				return "1".equals(getConfig(IS_TEST));
			}
			public static boolean isTomcat(){
				return "1".equals(getConfig(IS_TOMCAT));
			}
			public static String getImgUploadPath(){
				return getConfig(imgUploadPath);
			}
		}
		/*
		 * Json配置
		 */
		public static class Json{
			/*
			 *json保存地址
			 */
			public static final String jsonSavePath ="json.savePath";
			/**
			 * json读取地址
			 */
			public static final String jsonReadPath ="json.readPath";
			public static String getJsonSavePath(){
				return getConfig(jsonSavePath);
			}
			public static String getJsonReadPath(){
				return getConfig(jsonReadPath);
			}
		}
		/*
		 * 系统默认值
		 */
		public static class Defalut{
			/*
			 *求职会员默认会员类型
			 */
			public static final String defaltUserType1 ="defalt.UserType1";
			/*
			 *求职会员默认会员类型
			 */
			public static final String defaltUserType2 ="defalt.UserType2";
			/*
			 *求职会员默认会员组
			 */
			public static final String defaltGroup1 ="defalt.group1";
			/**
			 *公司会员默认会员组
			 */
			public static final String defaltGroup2 ="defalt.group2";
			public static Long getDefaltUserType1(){
				return Long.parseLong(getConfig(defaltUserType1));
			}
			public static Long getDefaltUserType2(){
				return Long.parseLong(getConfig(defaltUserType2));
			}
			public static Long getDefaltGroup1(){
				return Long.parseLong(getConfig(defaltGroup1));
			}
			public static Long getDefaltGroup2(){
				return Long.parseLong(getConfig(defaltGroup2));
			}
		}
		/*
		 * 短信设置
		 */
		public static class Sms{
		}
	}

	/**
	 * 配置文件
	 */
	public static final String CONFIG_FILE = "config.properties";

	/**
	 * 日志
	 */
	private static MyLogger logger = MyLogger.getLogger(ConfigUtil.class);

	/**
	 * Properties实例
	 */
	public static Properties props = new Properties();

	/**
	 * 构造方法
	 */
	private ConfigUtil() {

	}

	static {
		loadProperty();
	}

	/**
	 * 从配置文件中读取所有的属性
	 */
	public static void loadProperty() {
		try {
			InputStream file = new FileInputStream(ConfigUtil.class.getClassLoader().getResource("config.properties").getFile());
			props.clear();
			props.load(file);
			ICommService baseSetService = (ICommService)SpringHolder.getBean(ICommService.class);
			ParaMap pm=new ParaMap("select * from T_B_BASE_SET where status=1");
			List<BaseSet> subjectList = baseSetService.getBeanList(pm,BaseSet.class);
			for (BaseSet subject : subjectList) {
				if(!props.containsKey(subject.getBaseCode())){
					props.put(subject.getBaseCode(), StringUtils.NVL(subject.getBaseValue()));
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
		} else if(ret.indexOf("${")>-1){
			Matcher mat = paraPattern.matcher(ret);
		  	while(mat.find()){
		  		ret = ret.replaceAll("\\$\\{"+mat.group(1)+"\\}", getConfig(mat.group(1),""));
		  	}
		  	ret=ret.trim();
		  	props.setProperty(name, ret);
			return ret;
		}else{
			return ret.trim();
		}
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

}
