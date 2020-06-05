package com.dlz.test.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
@TableName("t_b_dict")
public class Dict {

    private String dictStatus;
    private String dictName;
    private String dictId;
    private String dictType;
}
