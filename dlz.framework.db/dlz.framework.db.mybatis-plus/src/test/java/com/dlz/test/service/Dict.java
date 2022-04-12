package com.dlz.test.service;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_b_dict")
public class Dict {
    private String dictStatus;
    private String dictName;
    private String dictId;
    private String dictType;
}
