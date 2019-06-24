package com.cseu.server.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: MybatisPlusConfig
 * @projectName cseu-user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1609:47
 */
@Configuration
@MapperScan("com.cseu.server.*.mapper.*")
public class MybatisPlusConfig {
    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
