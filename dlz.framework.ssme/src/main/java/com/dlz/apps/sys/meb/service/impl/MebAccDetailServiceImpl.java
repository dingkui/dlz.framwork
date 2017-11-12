package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MebAccDetailMapper;
import com.dlz.apps.sys.meb.model.MebAccDetail;
import com.dlz.apps.sys.meb.service.MebAccDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebAccDetailServiceImpl extends BaseServiceImpl<MebAccDetail, Long> implements MebAccDetailService {

    @Autowired
    public void setMapper(MebAccDetailMapper mapper) {
        this.mapper=mapper;
    }
}