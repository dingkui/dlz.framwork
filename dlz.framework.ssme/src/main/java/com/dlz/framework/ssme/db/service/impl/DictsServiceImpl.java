package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.DictsMapper;
import com.dlz.framework.ssme.db.model.Dicts;
import com.dlz.framework.ssme.db.service.DictsService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictsServiceImpl extends BaseServiceImpl<Dicts, Long> implements DictsService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    @Autowired
    public void setMapper(DictsMapper mapper) {
        this.mapper=mapper;
    }
}