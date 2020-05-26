package com.dlz.framework.db.modal;

import java.util.Map;

public interface IPara{
	IPara addParas(Map<String, Object> map);
	IPara addPara(String key, Object value);
}
