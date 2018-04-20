package com.dlz.commbiz.sys.rbac.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.LoginDeviceMapper;
import com.dlz.commbiz.sys.rbac.model.LoginDevice;
import com.dlz.commbiz.sys.rbac.service.LoginDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class LoginDeviceServiceImpl extends BaseServiceImpl<LoginDevice, Long> implements LoginDeviceService {

    @Autowired
    public void setMapper(LoginDeviceMapper mapper) {
        this.mapper=mapper;
    }
}