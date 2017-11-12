package com.dlz.apps.sys.meb.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sys.meb.dao.MebVcodeMapper;
import com.dlz.apps.sys.meb.model.MebVcode;
import com.dlz.apps.sys.meb.service.MebVcodeService;
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