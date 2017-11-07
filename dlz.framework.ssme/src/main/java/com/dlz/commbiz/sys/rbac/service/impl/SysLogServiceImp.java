package com.dlz.commbiz.sys.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.SysLogMapper;
import com.dlz.commbiz.sys.rbac.model.SysLog;
import com.dlz.commbiz.sys.rbac.service.SysLogService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysLogServiceImp extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {
	
	@Autowired
	public void setMapper(SysLogMapper mapper) {
		super.mapper = mapper;
	}
	
}
