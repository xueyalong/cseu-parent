package com.cseu.server.gateway.config;

import com.cseu.server.gateway.service.impl.UserDetailsServicIpml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


/**
 * @title: SecurityConfiguration
 * @projectName cseu-user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1521:40
 */


@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    // 请配置这个，以保证在刷新Token时能成功刷新
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        // 配置用户来源于数据库
        // 配置密码加密方式  BCryptPasswordEncoder，添加用户加密的时候请也用这个加密
        auth.userDetailsService(userDetailsService()).passwordEncoder( passwordEncoder());
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServicIpml();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/registry").permitAll() // 注册请求不需要验证
                .antMatchers("/sign_up").permitAll()
                .antMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/sign_in")
                .loginProcessingUrl("/login").defaultSuccessUrl("/personal_center",true)
                .failureUrl("/sign_in?error").permitAll()
                .and().logout().logoutSuccessUrl("/sign_in").permitAll()
                .and().csrf().disable();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()) // 用户认证
                .passwordEncoder( passwordEncoder()); // 使用加密验证
    }

}
