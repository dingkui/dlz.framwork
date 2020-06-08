package com.dlz.test.framework.springframework.iproxy;

import com.dlz.framework.spring.iproxy.anno.AnnoApi;

@AnnoApi(value = "",handler = "test")
public interface ITest2Api{
    public String sayHello(String a,String b);
    public String sayHello(String a);
    public String sayHello();
}