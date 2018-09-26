package com.dlz.framework.component;

import org.slf4j.Logger;
import com.dlz.framework.springframework.AnnoMyComponent;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.web.util.WxUtil;

/**
 *配置文件初始化
 */
@AnnoMyComponent
public class ConfigInit {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigInit.class);
	public ConfigInit(){
		WxUtil.init(ConfigUtil.getConfig("weixin.appids"), ConfigUtil.getConfig("setting.localhost"));
		logger.debug("资源初始化启动完成");
	}
}
