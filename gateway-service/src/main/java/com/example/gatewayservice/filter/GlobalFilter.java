package com.example.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request=exchange.getRequest();
            ServerHttpResponse response=exchange.getResponse();

            log.info("Global Filter baseMessage: {}", config.getBaseMessage()); //화면에 출력하고자 하는 메세지를 config에서 얻어와서 사용
            if(config.isPreLogger()) { //Pre 필터
                log.info("Global Filter Start: request id -> {}", config.getBaseMessage());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger()) { //Post 필터
                    log.info("Global Filter End: response code -> {}", response.getStatusCode());
                }
            }));
        });
    }

    @Data
    public static class Config { //필요한 환경설정있으면 쓰셈
        //.yml에서 필요한 설정 등록
        private String baseMessage; //base message
        private boolean preLogger; //Pre 로거 쓸 건지
        private boolean postLogger; //Post 로거 쓸 건지
    }
}
