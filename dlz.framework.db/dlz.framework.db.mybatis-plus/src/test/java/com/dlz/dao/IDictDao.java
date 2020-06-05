package com.dlz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dlz.test.service.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDictDao extends BaseMapper<Dict> {
}