package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MemMapper;
import com.dlz.commbiz.sys.meb.model.Mem;
import com.dlz.commbiz.sys.meb.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MemServiceImpl extends BaseServiceImpl<Mem, Long> implements MemService {

    @Autowired
    public void setMapper(MemMapper mapper) {
        this.mapper=mapper;
    }
}