package com.dlz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}