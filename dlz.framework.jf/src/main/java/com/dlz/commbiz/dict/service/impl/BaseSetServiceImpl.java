package com.dlz.commbiz.dict.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.dao.BaseSetMapper;
import com.dlz.commbiz.dict.model.BaseSet;
import com.dlz.commbiz.dict.service.BaseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class BaseSetServiceImpl extends BaseServiceImpl<BaseSet, String> implements BaseSetService {

    @Autowired
    public void setMapper(BaseSetMapper mapper) {
        this.mapper=mapper;
    }
}