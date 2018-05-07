package com.dlz.framework.ssme.util.config;

import com.dlz.common.util.config.ConfUtil;


public class ConfigUtil {
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
	 * 构造方法
	 */
	private ConfigUtil() {
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
		return ConfUtil.getConfig(name, defaultValue);
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

	public static void loadProperty() {
		ConfUtil.loadProperty();
	}

}
