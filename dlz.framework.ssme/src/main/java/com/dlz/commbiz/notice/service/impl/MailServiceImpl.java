package com.dlz.commbiz.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.notice.dao.MailMapper;
import com.dlz.commbiz.notice.model.Mail;
import com.dlz.commbiz.notice.service.MailService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MailServiceImpl extends BaseServiceImpl<Mail, Long> implements MailService {

    @Autowired
    public void setMapper(MailMapper mapper) {
        this.mapper=mapper;
    }
}