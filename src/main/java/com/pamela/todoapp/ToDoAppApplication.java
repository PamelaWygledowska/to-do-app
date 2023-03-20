package com.pamela.todoapp;

import com.pamela.todoapp.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ToDoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoAppApplication.class, args);
    }

}
