package com.dlz.framework.ssme.db.service;

import java.util.List;
import java.util.Map;

import com.dlz.framework.ssme.db.model.ComboBoxModel;

public interface DictServiceExt  {

	public Map<String, String> getDictMap(Class<?> clazz);
	
	public List<ComboBoxModel> getDictDetails(String dictCode);
	
	public String getNameDesc(String dictCode,String dictValue);
}