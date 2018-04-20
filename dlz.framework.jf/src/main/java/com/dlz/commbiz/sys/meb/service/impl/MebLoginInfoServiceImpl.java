package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MebLoginInfoMapper;
import com.dlz.commbiz.sys.meb.model.MebLoginInfo;
import com.dlz.commbiz.sys.meb.service.MebLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebLoginInfoServiceImpl extends BaseServiceImpl<MebLoginInfo, Long> implements MebLoginInfoService {

    @Autowired
    public void setMapper(MebLoginInfoMapper mapper) {
        this.mapper=mapper;
    }
}