package com.dlz.test.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dlz.framework.util.system.Reflections;

public class MainTest {

    public static void main(String[] args) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dict::getDictStatus,1);
        Class<Dict> clazz = (Class<Dict>) Reflections.getClassGenricType(queryWrapper.getClass(),0);
        System.out.println(clazz);
    }

}