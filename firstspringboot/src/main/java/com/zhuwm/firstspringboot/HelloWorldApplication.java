package com.zhuwm.firstspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class HelloWorldApplication {

    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}