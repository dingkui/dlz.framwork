package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MemAccountMapper;
import com.dlz.commbiz.sys.meb.model.MemAccount;
import com.dlz.commbiz.sys.meb.service.MemAccountService;
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