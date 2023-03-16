package com.example.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> { //우리가 지금 만들고 있는 CustomFilter의 Config
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) { //우리가 적용하고 싶은 내용
        // Custom Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest(); //exchage로부터 request 받아오기
            ServerHttpResponse response = exchange.getResponse(); //response 받아오기

            log.info("Custom PRE filter: request id -> {}", request.getId());

            // Custom Post Filter - 처리 후
            return chain.filter(exchange).then(Mono.fromRunnable(() -> { //chain에 연결 -> 필터 추가
                // Mono : 데이터 타입 하나 줄 거임
                // 비동기 방식에서 단일값일 때 사용, response 할 때 response status code를 출력
                log.info("Custom POST filter: response code -> {}", response.getStatusCode());
            }));
        };
    }

    public static class Config {
        // Put the configuration properties
    }
}