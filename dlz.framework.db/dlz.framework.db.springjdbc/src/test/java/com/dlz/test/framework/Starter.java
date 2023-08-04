package com.dlz.test.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}