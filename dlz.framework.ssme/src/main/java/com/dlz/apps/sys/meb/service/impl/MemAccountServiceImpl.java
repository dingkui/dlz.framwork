package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MemAccountMapper;
import com.dlz.apps.sys.meb.model.MemAccount;
import com.dlz.apps.sys.meb.service.MemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MemAccountServiceImpl extends BaseServiceImpl<MemAccount, Long> implements MemAccountService {

    @Autowired
    public void setMapper(MemAccountMapper mapper) {
        this.mapper=mapper;
    }
}