package com.dlz.test.framework.holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BeanClass2 {
    public BeanClass1 getBeanClass1() {
        return beanClass1;
    }


    @Autowired
    BeanClass1 beanClass1;
}
