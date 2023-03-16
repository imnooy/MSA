package com.example.myzuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    @Override
    public Object run() throws ZuulException {
        log.info("**************** printing logs: ");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest(); //http 요청에 대한 로그 출력
        log.info("**************** " + request.getRequestURI());

        return null;
    }

    @Override
    public String filterType() {
        return "pre"; //"pre"는 사전 필터, "post"는 사후 필터
    }

    @Override
    public int filterOrder() { //필터가 여러개 있을 때 이 필터의 순위
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true; //필터를 쓰겠다.
    }
}
