package com.dlz.framework.component;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.springframework.AnnoMyComponent;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.web.util.WxUtil;

/**
 *配置文件初始化
 */
@AnnoMyComponent
public class ConfigInit {
	private static MyLogger logger = MyLogger.getLogger(ConfigInit.class);
	public ConfigInit(){
		WxUtil.init(ConfigUtil.getConfig("weixin.appids"), ConfigUtil.getConfig("setting.localhost"));
		logger.debug("资源初始化启动完成");
	}
}
