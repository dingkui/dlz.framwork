package com.dlz.framework.db.modal;

import java.util.Map;

public interface IPara{
	public IPara addParas(Map<String, Object> map);	
	public IPara addPara(String key,Object value);
}
