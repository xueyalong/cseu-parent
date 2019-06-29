
package com.cseu.server.gateway.auth.basic;

import com.cseu.server.gateway.auth.jwt.JWTTokenService;
import com.cseu.server.user.api.CseuGuestRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class BasicAuthenticationSuccessHandler
        implements ServerAuthenticationSuccessHandler {


    @Autowired
    private CseuGuestRpcService cseuGuestRpcService;
    /**
     * A successful authentication object us used to create a JWT object and
     * added in the authorization header of the current WebExchange
     *
     * @param webFilterExchange
     * @param authentication
     * @return
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        //TODO refactor this nasty implementation
        log.error("**************************** Authentication ={}  *****************",authentication.getName());
        exchange.getResponse()
                .getHeaders()
                .add(HttpHeaders.AUTHORIZATION, getHttpAuthHeaderValue(authentication));
        return webFilterExchange.getChain().filter(exchange);
    }

    private static String getHttpAuthHeaderValue(Authentication authentication){

        return String.join(" ","Bearer",tokenFromAuthentication(authentication));
    }

    private static String tokenFromAuthentication(Authentication authentication){
        return JWTTokenService.generateToken(
                                            authentication.getName(),
                                            authentication.getCredentials(),
                                            authentication.getAuthorities());
    }
}
