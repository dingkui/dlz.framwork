package com.dlz.apps.notice.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.notice.db.dao.AnnounceMapper;
import com.dlz.apps.notice.db.model.Announce;
import com.dlz.apps.notice.db.service.AnnounceService;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;

@Service
@Transactional(rollbackFor=Exception.class)
public class AnnounceServiceImpl extends BaseServiceImpl<Announce, Long> implements AnnounceService {

    @Autowired
    public void setMapper(AnnounceMapper mapper) {
        this.mapper=mapper;
    }
}