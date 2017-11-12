package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MebMapper;
import com.dlz.apps.sys.meb.model.Meb;
import com.dlz.apps.sys.meb.service.MebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebServiceImpl extends BaseServiceImpl<Meb, Long> implements MebService {

    @Autowired
    public void setMapper(MebMapper mapper) {
        this.mapper=mapper;
    }
}