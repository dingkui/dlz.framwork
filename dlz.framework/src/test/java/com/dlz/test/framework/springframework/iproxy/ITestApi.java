package com.dlz.test.framework.springframework.iproxy;

import com.dlz.framework.spring.iproxy.anno.AnnoApi;

@AnnoApi(value = "",handler = "test")
public interface ITestApi{
    public String sayHello(String a,String b);
}