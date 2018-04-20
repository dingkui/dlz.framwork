package com.dlz.commbiz.sys.rbac.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.FunOptMapper;
import com.dlz.commbiz.sys.rbac.dao.RbacMapper;
import com.dlz.commbiz.sys.rbac.model.FunOpt;
import com.dlz.commbiz.sys.rbac.service.FunOptService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FunOptServiceImpl extends BaseServiceImpl<FunOpt, Long> implements FunOptService {
	
	@Autowired
	private RbacMapper rbacMapper;
	
    @Autowired
    public void setMapper(FunOptMapper mapper) {
        this.mapper=mapper;
    }
  	public List<String> getFunOptUrlByUserId(Long userId) {
  		return rbacMapper.getFunOptUrlByUserId(userId);
  	}
  	public List<Map<String, Object>> getOptsByRoles(Map para) {
  		return rbacMapper.getOptsByRoles(para);
  	}
}