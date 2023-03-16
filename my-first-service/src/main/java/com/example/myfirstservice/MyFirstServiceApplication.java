package com.example.myfirstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyFirstServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFirstServiceApplication.class, args);
    }

}
