package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MebLoginInfoMapper;
import com.dlz.apps.sys.meb.model.MebLoginInfo;
import com.dlz.apps.sys.meb.service.MebLoginInfoService;
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