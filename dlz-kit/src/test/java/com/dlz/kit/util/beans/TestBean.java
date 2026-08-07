package com.dlz.kit.util.beans;

import com.dlz.kit.util.system.annotation.SetValue;
import lombok.Data;

@Data
public class TestBean {
    private String name = "测试名称";
    @SetValue("info")
    private String xx;
    @SetValue("info")
    private String xx2;
}