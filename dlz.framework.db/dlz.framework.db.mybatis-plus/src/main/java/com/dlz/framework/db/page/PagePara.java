package com.dlz.framework.db.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收参数的分页对象
 *
 * @author dk
 * @since 2020-01-15
 */
@Data
@ApiModel(value = "分页参数对象3")
public class PagePara<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "当前页码", position = 1)
    private int pageNum=1;
    @ApiModelProperty(value = "每页条数", position = 2)
    private int pageSize=20;
    @ApiModelProperty(value = "排序", position = 3)
    private List<OrderItem> orders = new ArrayList<>();
    @ApiModelProperty(value = "查询参数", position = 4)
    private T eq;
    @ApiModelProperty(value = "其他查询参数", position = 5)
    private Map<String,Object> para=new HashMap<>();
}
