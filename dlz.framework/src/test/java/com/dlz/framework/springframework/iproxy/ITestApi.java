package com.dlz.framework.springframework.iproxy;

import com.dlz.framework.springframework.iproxy.anno.AnnoApi;

@AnnoApi(value = "",handler = "test")
public interface ITestApi{
    public String sayHello(String a,String b);
}