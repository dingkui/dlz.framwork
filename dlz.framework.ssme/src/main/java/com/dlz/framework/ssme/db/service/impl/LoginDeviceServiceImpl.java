package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.LoginDeviceMapper;
import com.dlz.framework.ssme.db.model.LoginDevice;
import com.dlz.framework.ssme.db.service.LoginDeviceService;

@Service
@Transactional(rollbackFor=Exception.class)
public class LoginDeviceServiceImpl extends BaseServiceImpl<LoginDevice, Long> implements LoginDeviceService {

    @Autowired
    public void setMapper(LoginDeviceMapper mapper) {
        this.mapper=mapper;
    }
}