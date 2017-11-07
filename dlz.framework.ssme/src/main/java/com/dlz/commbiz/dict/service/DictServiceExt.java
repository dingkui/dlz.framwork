package com.dlz.commbiz.dict.service;

import java.util.List;
import java.util.Map;

import com.dlz.commbiz.dict.model.ComboBoxModel;

public interface DictServiceExt  {

	public Map<String, String> getDictMap(Class<?> clazz);
	
	public List<ComboBoxModel> getDictDetails(String dictCode);
	
	public String getNameDesc(String dictCode,String dictValue);
}