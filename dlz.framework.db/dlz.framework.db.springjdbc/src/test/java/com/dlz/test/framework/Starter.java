package com.dlz.test.framework;

//import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.service.ICommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Starter {
    @Autowired
    ICommService commService;
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
    @PostConstruct
    void init(){
//        String sql = DbInfo.getSql("key.setting.getSettings");
//        System.out.println(sql);
    }
}