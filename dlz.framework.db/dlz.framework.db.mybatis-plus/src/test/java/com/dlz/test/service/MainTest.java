package com.dlz.test.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dlz.framework.db.util.DbUtil;
import com.dlz.framework.util.system.Reflections;
import com.dlz.test.db.entity.Dict;

public class MainTest {

    public static void main(String[] args) {
        Dict bean = new Dict();
        LambdaQueryWrapper<Dict> lambda = new LambdaQueryWrapper<>(Dict.class);
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>(){};
//        queryWrapper.eq(Dict::getDictStatus,1);
        Class<?> clazz = (Class<Dict>) Reflections.getClassGenricType(lambda.getClass(),0);
//        System.out.println(clazz);

//        System.out.println(lambda.getEntityClass());
        System.out.println(DbUtil.getEntityClass(new QueryWrapper<>(bean)));
        System.out.println(DbUtil.getEntityClass(new LambdaQueryWrapper<>(Dict.class)));
        System.out.println(DbUtil.getEntityClass(DbUtil.mkWrapper(Dict.class)));
        System.out.println(DbUtil.getEntityClass(DbUtil.mkLambdaWrapper(Dict.class)));
        System.out.println(DbUtil.getEntityClass(new LambdaQueryWrapper<Dict>(){}));
        System.out.println(DbUtil.getEntityClass(new QueryWrapper<Dict>(){}));
        System.out.println(DbUtil.getEntityClass(new QueryWrapper<Dict>()));
    }

}