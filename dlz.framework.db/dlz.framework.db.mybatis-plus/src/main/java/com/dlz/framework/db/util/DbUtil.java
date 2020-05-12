package com.dlz.framework.db.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.dlz.comm.util.StringUtils;

import java.util.List;

/**
 * 数据库查询工具
 *
 * @author dk
 * @since 2020-01-15
 */
public class DbUtil {
    public static <T> QueryWrapper<T> mkWrapper(QueryWrapper<T> queryWrapper, T bean, List<OrderItem> orders) {
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper();
        }
        if (bean != null) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(bean.getClass());
            Object keyValue = ReflectionKit.getMethodValue(bean, tableInfo.getKeyProperty());
            if (StringUtils.isNotEmpty(keyValue)) {
                queryWrapper.eq(tableInfo.getKeyColumn(), keyValue);
            } else {
                for (TableFieldInfo field : tableInfo.getFieldList()) {
                    Object value = ReflectionKit.getMethodValue(bean, field.getProperty());
                    if (StringUtils.isNotEmpty(value)) {
                        queryWrapper.eq(field.getColumn(), value);
                    }
                }
            }
        }
        if (orders != null) {
            for (OrderItem orderItem : orders) {
                if (StringUtils.isEmpty(orderItem.getColumn())) {
                    continue;
                }
                if (orderItem.isAsc()) {
                    queryWrapper.orderByAsc(orderItem.getColumn());
                } else {
                    queryWrapper.orderByDesc(orderItem.getColumn());
                }
            }
        }
        return queryWrapper;
    }

    public static <T> QueryWrapper<T> mkWrapper(T bean) {
        return mkWrapper(null, bean, null);
    }
}
