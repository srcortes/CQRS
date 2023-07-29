package com.springbank.user.query.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("${component.scan}")
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class);
    }
}
