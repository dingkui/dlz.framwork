package com.dlz.framework.ssme.db.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.FunOptMapper;
import com.dlz.framework.ssme.db.dao.RbacMapper;
import com.dlz.framework.ssme.db.model.FunOpt;
import com.dlz.framework.ssme.db.service.FunOptService;

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