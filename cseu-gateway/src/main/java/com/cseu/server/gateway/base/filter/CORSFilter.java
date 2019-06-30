package com.cseu.server.gateway.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.cseu.server.gateway.auth.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @title: CORSFilter
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-3003:45
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CORSFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();

        if (CorsUtils.isCorsRequest(request)) {
            ServerHttpResponse response = serverWebExchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "*");
            headers.add("Access-Control-Max-Age", "3600");
            headers.add("Access-Control-Allow-Headers", "my-token");
            headers.add("Access-Control-Allow-Headers", "Content-Type");
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
        }
        if(request.getPath().value().contains("auth")||request.getPath().value().contains("actuator")){
            return webFilterChain.filter(serverWebExchange);
        }
        ServerHttpResponse response=serverWebExchange.getResponse();
        String authorization=request.getHeaders().getFirst("Authorization");
        log.info("token={}",authorization);
        if(authorization == null || ! authorization.startsWith("Bearer ")){
            return this.setErrorResponse(response,"未携带token");
        }
        String token=authorization.substring(7);
        try {
            serverWebExchange.getAttributes().put("user", Jwt.parseJwt(token));
        }catch(Exception e) {
            return this.setErrorResponse(response,e.getMessage());
        }
        return  webFilterChain.filter(serverWebExchange);

    }
    protected Mono<Void> setErrorResponse(ServerHttpResponse response, String message){
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status_code",500);
        jsonObject.put("data",message);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(jsonObject.toString().getBytes())));

    }


}
