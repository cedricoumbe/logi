package com.example.demo7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
public class Demo7Application extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(Demo7Application.class, args);
    }

}
