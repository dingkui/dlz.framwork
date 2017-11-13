package com.dlz.framework.ssme.db.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.DictsMapper;
import com.dlz.framework.ssme.db.model.Dicts;
import com.dlz.framework.ssme.db.service.DictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictsServiceImpl extends BaseServiceImpl<Dicts, Long> implements DictsService {

    @Autowired
    public void setMapper(DictsMapper mapper) {
        this.mapper=mapper;
    }
}