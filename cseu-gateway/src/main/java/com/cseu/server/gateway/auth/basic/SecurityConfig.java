package com.cseu.server.gateway.auth.basic;

import com.cseu.server.gateway.auth.AuthenticationManager;
import com.cseu.server.gateway.auth.CseuAuthenticationSuccessHandler;
import com.cseu.server.gateway.auth.SecurityContextRepository;
import com.cseu.server.gateway.service.CseuReactiveUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.reactive.config.WebFluxConfigurer;

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
@EnableReactiveMethodSecurity
@Slf4j
public class SecurityConfig implements WebFluxConfigurer {


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityContextRepository securityContextRepository;
    @Autowired
    private CseuAuthenticationSuccessHandler cseuAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        log.info("WebFlux Security begin");
        return http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .securityContextRepository(securityContextRepository)
                .authenticationManager(authenticationManager)
//                .authenticationSuccessHandler(cseuAuthenticationSuccessHandler )
//                .and()
                .authorizeExchange()
                .pathMatchers("/u/u/actuator/health").permitAll()
                .pathMatchers("/favicon.ico").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .anyExchange().authenticated()
                .and().build();
        //.loginPage("/loginPage")  //自定义的登陆页面


    }

    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new CseuReactiveUserDetailsServiceImpl();
    }

}
