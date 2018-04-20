package com.dlz.commbiz.sets.service.impl;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sets.dao.ProvinceAndCityMapper;
import com.dlz.commbiz.sets.model.ProvinceAndCity;
import com.dlz.commbiz.sets.service.ProvinceAndCityService;
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