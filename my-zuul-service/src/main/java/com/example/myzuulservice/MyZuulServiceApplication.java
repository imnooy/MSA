package com.example.myzuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MyZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyZuulServiceApplication.class, args);
	}

}
