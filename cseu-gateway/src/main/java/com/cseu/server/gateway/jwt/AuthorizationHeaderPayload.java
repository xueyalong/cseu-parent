package com.cseu.server.gateway.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @title: AuthorizationHeaderPayload
 * @projectName demo
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2816:54
 */
public class AuthorizationHeaderPayload {
    public static Mono<String> extract(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION));
    }
}
