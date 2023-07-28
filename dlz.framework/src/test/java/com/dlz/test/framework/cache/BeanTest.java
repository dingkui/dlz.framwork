package com.dlz.test.framework.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BeanTest implements Serializable {
    String a;
    String b;
    public BeanTest(){}
}
