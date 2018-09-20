package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.DictDetailMapper;
import com.dlz.framework.ssme.db.model.DictDetail;
import com.dlz.framework.ssme.db.model.DictDetailKey;
import com.dlz.framework.ssme.db.service.DictDetailService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictDetailServiceImpl extends BaseServiceImpl<DictDetail, DictDetailKey> implements DictDetailService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    @Autowired
    public void setMapper(DictDetailMapper mapper) {
        this.mapper=mapper;
    }
}