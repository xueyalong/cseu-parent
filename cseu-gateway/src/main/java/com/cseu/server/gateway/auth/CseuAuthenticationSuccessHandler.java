package com.cseu.server.gateway.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * @title: CseuAuthenticationSuccessHandler
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-3012:18
 */
@Slf4j
@Component
public class CseuAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        exchange.getResponse()
                .getHeaders()
                .add(HttpHeaders.AUTHORIZATION, getHttpAuthHeaderValue(authentication));
        return webFilterExchange.getChain().filter(exchange);
    }
    private static String getHttpAuthHeaderValue(Authentication authentication){

        return String.join(" ","Bearer",tokenFromAuthentication(authentication));
    }

    private static String tokenFromAuthentication(Authentication authentication){
        log.info("========="+authentication.getName(),authentication.getCredentials().toString(), authentication.getAuthorities().toString());
        return Jwt.createJwt(
                authentication.getName(),
                authentication.getCredentials().toString(),
                authentication.getAuthorities().toString(),10000);
    }
}
