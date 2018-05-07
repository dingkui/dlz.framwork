package com.dlz.framework.springframework.iproxy;

import com.dlz.framework.springframework.iproxy.anno.AnnoInterfaceDeal;

@AnnoInterfaceDeal("testHandler")
public interface ITest2Deal{
    public String sayHello(String a,String b);
    public String sayHello(String a);
    public String sayHello();
}