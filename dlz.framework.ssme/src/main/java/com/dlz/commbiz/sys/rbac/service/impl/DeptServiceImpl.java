package com.dlz.commbiz.sys.rbac.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.DeptMapper;
import com.dlz.commbiz.sys.rbac.model.Dept;
import com.dlz.commbiz.sys.rbac.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class DeptServiceImpl extends BaseServiceImpl<Dept, Long> implements DeptService {

    @Autowired
    public void setMapper(DeptMapper mapper) {
        this.mapper=mapper;
    }
}