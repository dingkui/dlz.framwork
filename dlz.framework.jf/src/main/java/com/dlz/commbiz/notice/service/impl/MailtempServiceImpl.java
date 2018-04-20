package com.dlz.commbiz.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.notice.dao.MailtempMapper;
import com.dlz.commbiz.notice.model.Mailtemp;
import com.dlz.commbiz.notice.service.MailtempService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MailtempServiceImpl extends BaseServiceImpl<Mailtemp, Long> implements MailtempService {

    @Autowired
    public void setMapper(MailtempMapper mapper) {
        this.mapper=mapper;
    }
}