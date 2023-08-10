package com.dlz.test.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_b_dict")
@ApiModel("测试")
public class Dict {
    @ApiModelProperty("xasd：啊实打实\n\"xas\"")
    private String dictStatus;
    @ApiModelProperty("int")
    private int a2;
    @ApiModelProperty("boolean：")
    private boolean a3;
    @ApiModelProperty("Long：")
    private Long a4;
    @ApiModelProperty("Float：")
    private Float a5;
    @ApiModelProperty("BigDecimal：")
    private BigDecimal a6;
    @ApiModelProperty("Object：")
    private Object a7;
    @ApiModelProperty("Date：")
    private Date a8;
    @ApiModelProperty("java.sql.Date：")
    private java.sql.Date a9;
}
