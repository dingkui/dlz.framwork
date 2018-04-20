package com.dlz.commbiz.sys.meb.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.meb.dao.MebVcodeMapper;
import com.dlz.commbiz.sys.meb.model.MebVcode;
import com.dlz.commbiz.sys.meb.service.MebVcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MebVcodeServiceImpl extends BaseServiceImpl<MebVcode, Long> implements MebVcodeService {

    @Autowired
    public void setMapper(MebVcodeMapper mapper) {
        this.mapper=mapper;
    }
}