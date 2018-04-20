package com.dlz.commbiz.dict.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.dao.DictMapper;
import com.dlz.commbiz.dict.model.Dict;
import com.dlz.commbiz.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictServiceImpl extends BaseServiceImpl<Dict, String> implements DictService {

    @Autowired
    public void setMapper(DictMapper mapper) {
        this.mapper=mapper;
    }
}