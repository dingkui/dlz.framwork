package com.dlz.framework.ssme.interfaces;

import java.util.Map;

public interface IConfigInterface {
	String getNameByKey(String valueOf, String para);

	Map<String,String> getConfigMap();
}