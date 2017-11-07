package com.dlz.common.interfaces;

import java.util.Map;

public interface IConfigInterface {
	String getNameByKey(String valueOf, String para);

	Map<String,String> getConfigMap();
}