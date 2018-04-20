package com.dlz.commbiz.sys.rbac.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.DeptUserMapper;
import com.dlz.commbiz.sys.rbac.model.DeptUser;
import com.dlz.commbiz.sys.rbac.service.DeptUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class DeptUserServiceImpl extends BaseServiceImpl<DeptUser, Long> implements DeptUserService {

    @Autowired
    public void setMapper(DeptUserMapper mapper) {
        this.mapper=mapper;
    }
}