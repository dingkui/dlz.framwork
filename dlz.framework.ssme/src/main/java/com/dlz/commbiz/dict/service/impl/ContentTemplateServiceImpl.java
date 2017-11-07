package com.dlz.commbiz.dict.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.dao.ContentTemplateMapper;
import com.dlz.commbiz.dict.model.ContentTemplate;
import com.dlz.commbiz.dict.service.ContentTemplateService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContentTemplateServiceImpl extends BaseServiceImpl<ContentTemplate, Long> implements ContentTemplateService {
	@Autowired
	public void setMapper(ContentTemplateMapper contentTemplateMapper) {
		super.mapper = contentTemplateMapper;
	}

}
