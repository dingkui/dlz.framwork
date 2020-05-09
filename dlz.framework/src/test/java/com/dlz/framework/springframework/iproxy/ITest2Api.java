package com.dlz.framework.springframework.iproxy;

import com.dlz.framework.springframework.iproxy.anno.AnnoApi;

@AnnoApi(value = "",handler = "test")
public interface ITest2Api{
    public String sayHello(String a,String b);
    public String sayHello(String a);
    public String sayHello();
}