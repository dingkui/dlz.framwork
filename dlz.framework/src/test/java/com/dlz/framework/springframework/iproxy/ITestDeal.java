package com.dlz.framework.springframework.iproxy;

import com.dlz.framework.springframework.iproxy.anno.AnnoInterfaceDeal;

@AnnoInterfaceDeal("testHandler")
public interface ITestDeal{
    public String sayHello(String a,String b);
}