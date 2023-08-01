package com.springbank.user.query.api;

import com.springbank.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("${component.scan}")
@Import({AxonConfig.class})
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class);
    }
}
