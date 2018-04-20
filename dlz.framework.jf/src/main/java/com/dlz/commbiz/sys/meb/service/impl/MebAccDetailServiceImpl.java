package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MebAccDetailMapper;
import com.dlz.commbiz.sys.meb.model.MebAccDetail;
import com.dlz.commbiz.sys.meb.service.MebAccDetailService;
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