package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MebAccountMapper;
import com.dlz.commbiz.sys.meb.model.MebAccount;
import com.dlz.commbiz.sys.meb.service.MebAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebAccountServiceImpl extends BaseServiceImpl<MebAccount, Long> implements MebAccountService {

    @Autowired
    public void setMapper(MebAccountMapper mapper) {
        this.mapper=mapper;
    }
}