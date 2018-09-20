package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.DeptUserMapper;
import com.dlz.framework.ssme.db.model.DeptUser;
import com.dlz.framework.ssme.db.service.DeptUserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DeptUserServiceImpl extends BaseServiceImpl<DeptUser, Long> implements DeptUserService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    @Autowired
    public void setMapper(DeptUserMapper mapper) {
        this.mapper=mapper;
    }
}