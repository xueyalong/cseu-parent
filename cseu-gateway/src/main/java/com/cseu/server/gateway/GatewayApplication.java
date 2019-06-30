package com.cseu.server.gateway;


import com.cseu.server.gateway.base.filter.SystemRedisRateLimiter;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.util.List;


@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
@MapperScan(basePackages = "com.cseu.server.gateway.*")
@EnableFeignClients(basePackages = "com.cseu.server.*.api")
public class GatewayApplication {
    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);


    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class).run(args);
    }

    @Bean
    KeyResolver sysKeyResolver() {
        //从请求地址中截取sys值，进行限流。
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    @Bean
    @Primary
    //使用自己定义的限流类
    SystemRedisRateLimiter systemRedisRateLimiter(
            ReactiveRedisTemplate<String, String> redisTemplate,
            @Qualifier(SystemRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> script,
            Validator validator) {
        return new SystemRedisRateLimiter(redisTemplate, script, validator);
    }



}