package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.ContentTemplateMapper;
import com.dlz.framework.ssme.db.model.ContentTemplate;
import com.dlz.framework.ssme.db.service.ContentTemplateService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContentTemplateServiceImpl extends BaseServiceImpl<ContentTemplate, Long> implements ContentTemplateService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	public void setMapper(ContentTemplateMapper contentTemplateMapper) {
		super.mapper = contentTemplateMapper;
	}

}
