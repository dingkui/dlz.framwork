package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MebLinkedMapper;
import com.dlz.apps.sys.meb.model.MebLinked;
import com.dlz.apps.sys.meb.service.MebLinkedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebLinkedServiceImpl extends BaseServiceImpl<MebLinked, Long> implements MebLinkedService {

    @Autowired
    public void setMapper(MebLinkedMapper mapper) {
        this.mapper=mapper;
    }
}