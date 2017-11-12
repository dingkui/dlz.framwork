package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.SysLogMapper;
import com.dlz.framework.ssme.db.model.SysLog;
import com.dlz.framework.ssme.db.service.SysLogService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysLogServiceImp extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {
	
	@Autowired
	public void setMapper(SysLogMapper mapper) {
		super.mapper = mapper;
	}
	
}
