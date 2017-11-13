package com.dlz.apps.sets.db.service.impl;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.apps.sets.db.dao.ProvinceAndCityMapper;
import com.dlz.apps.sets.db.model.ProvinceAndCity;
import com.dlz.apps.sets.db.service.ProvinceAndCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProvinceAndCityServiceImpl extends BaseServiceImpl<ProvinceAndCity, Long> implements ProvinceAndCityService {

    @Autowired
    public void setMapper(ProvinceAndCityMapper mapper) {
        this.mapper=mapper;
    }
}