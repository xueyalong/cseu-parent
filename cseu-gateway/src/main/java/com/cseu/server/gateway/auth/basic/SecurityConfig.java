package com.cseu.server.gateway.auth.basic;

import com.cseu.server.gateway.auth.bearer.BearerTokenReactiveAuthenticationManager;
import com.cseu.server.user.api.CseuGuestRpcService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @title: SecurityConfig
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2819:36
 */
@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {



    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        log.info("WebFlux Security begin");
        return http
                .authorizeExchange()
                .pathMatchers("/admin/**")
                .authenticated()
                .pathMatchers("/**")
                .permitAll()
                .and()
                .csrf()
                //.csrfTokenRepository(customCsrfTokenRepository)
                //.requireCsrfProtectionMatcher(customCsrfMatcher)
                .and()
                .formLogin()
                //.loginPage("/login")
                //.authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/login?error"))
                //.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/admin"))
                .and()
                .logout()
                //.logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler("/login?logout"))
                .and()
                .build();
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        //内存中缓存权限数据
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder.username("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
        // 输出加密密码
        String encodePassword = passwordEncoder.encode("123456");
        log.info("encodePassword:" + encodePassword);
        return new MapReactiveUserDetailsService(admin);
    }

}
