package com.example.mysecondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MySecondServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecondServiceApplication.class, args);
	}

}
