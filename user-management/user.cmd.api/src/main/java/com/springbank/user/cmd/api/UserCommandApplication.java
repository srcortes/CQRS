package com.springbank.user.cmd.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("${component.scan}")
public class UserCommandApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class);
    }
}
