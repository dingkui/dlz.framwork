package com.dlz.commbiz.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.commbiz.notice.dao.AnnounceMapper;
import com.dlz.commbiz.notice.model.Announce;
import com.dlz.commbiz.notice.service.AnnounceService;
import com.dlz.common.base.service.impl.BaseServiceImpl;

@Service
@Transactional(rollbackFor=Exception.class)
public class AnnounceServiceImpl extends BaseServiceImpl<Announce, Long> implements AnnounceService {

    @Autowired
    public void setMapper(AnnounceMapper mapper) {
        this.mapper=mapper;
    }
}