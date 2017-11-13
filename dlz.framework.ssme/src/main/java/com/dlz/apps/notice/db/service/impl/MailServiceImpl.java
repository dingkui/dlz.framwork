package com.dlz.apps.notice.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.notice.db.dao.MailMapper;
import com.dlz.apps.notice.db.model.Mail;
import com.dlz.apps.notice.db.service.MailService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MailServiceImpl extends BaseServiceImpl<Mail, Long> implements MailService {

    @Autowired
    public void setMapper(MailMapper mapper) {
        this.mapper=mapper;
    }
}