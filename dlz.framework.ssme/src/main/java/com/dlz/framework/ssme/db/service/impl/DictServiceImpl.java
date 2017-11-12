package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.DictMapper;
import com.dlz.framework.ssme.db.model.Dict;
import com.dlz.framework.ssme.db.service.DictService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictServiceImpl extends BaseServiceImpl<Dict, String> implements DictService {

    @Autowired
    public void setMapper(DictMapper mapper) {
        this.mapper=mapper;
    }
}