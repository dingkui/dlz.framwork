package com.dlz.framework.db.helper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("dlz.db.helper")
public class HelperProperties {
	String packageName="org.springblade.scgl";
	boolean autoUpdate=true;
	String type="postgresql";
	int maxPoolSize=3;
	int corePoolSize=1;
}
