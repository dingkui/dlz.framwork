package com.dlz.commbiz.dict.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.dict.dao.DictDetailMapper;
import com.dlz.commbiz.dict.model.DictDetail;
import com.dlz.commbiz.dict.model.DictDetailKey;
import com.dlz.commbiz.dict.service.DictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class DictDetailServiceImpl extends BaseServiceImpl<DictDetail, DictDetailKey> implements DictDetailService {

    @Autowired
    public void setMapper(DictDetailMapper mapper) {
        this.mapper=mapper;
    }
}