package com.dlz.commbiz.dict.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.dao.DictsMapper;
import com.dlz.commbiz.dict.model.Dicts;
import com.dlz.commbiz.dict.service.DictsService;
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