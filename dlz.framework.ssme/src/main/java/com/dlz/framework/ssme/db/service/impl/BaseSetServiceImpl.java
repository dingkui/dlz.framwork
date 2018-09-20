package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.BaseSetMapper;
import com.dlz.framework.ssme.db.model.BaseSet;
import com.dlz.framework.ssme.db.service.BaseSetService;

@Service
@Transactional(rollbackFor=Exception.class)
public class BaseSetServiceImpl extends BaseServiceImpl<BaseSet, String> implements BaseSetService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    @Autowired
    public void setMapper(BaseSetMapper mapper) {
        this.mapper=mapper;
    }
}