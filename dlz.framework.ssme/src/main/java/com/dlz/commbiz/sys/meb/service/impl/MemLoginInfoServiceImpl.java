package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MemLoginInfoMapper;
import com.dlz.commbiz.sys.meb.model.MemLoginInfo;
import com.dlz.commbiz.sys.meb.service.MemLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MemLoginInfoServiceImpl extends BaseServiceImpl<MemLoginInfo, Long> implements MemLoginInfoService {

    @Autowired
    public void setMapper(MemLoginInfoMapper mapper) {
        this.mapper=mapper;
    }
}