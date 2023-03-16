package com.example.myfirstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/first-service")
public class FirstServiceController {
    Environment env;

    @Autowired
    public FirstServiceController(Environment env) { //생성자 통해 환경변수 주입받기
        this.env=env;
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Frist service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        System.out.println(header);
        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return String.format("Hi, there. This is a mesasge from First Service on PORT %s", env.getProperty("local.server.port"));
    }
}
