package com.cseu.server.gateway.auth;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: AuthenticationManager
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-3011:32
 */

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToekn = authentication.getCredentials().toString();
        try {
            Claims claims = Jwt.parseJwt(authToekn);
            //todo 此处应该列出token中携带的角色表。
            log.info("claims id is{},authentication={}",claims.getId(),authentication.getAuthorities().stream().findFirst());
            Authentication authentication1 = new UsernamePasswordAuthenticationToken(
                    claims.getId(),
                    null,
                    authentication.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(((GrantedAuthority) role).getAuthority())).collect(Collectors.toList())
            );
            return Mono.just(authentication1);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

}
