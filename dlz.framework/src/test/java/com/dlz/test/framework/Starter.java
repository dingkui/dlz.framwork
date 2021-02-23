package com.dlz.test.framework;

import com.dlz.comm.util.web.HttpEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Starter {

    public static void main(String[] args) {
//        SpringApplication.run(Starter.class, args);
        System.out.println((String)HttpEnum.POST.send("http://dk.d.shunliannet.com"));
    }
}