package com.dlz.commbiz.freemaker.service;

import java.io.OutputStream;
import java.util.Map;

public interface TemplateService {

	String getText(String templateId, Map<Object, Object> parameters);
	
	void getOutputStream(String templateId, Map<Object, Object> parameters, OutputStream fos);

}
