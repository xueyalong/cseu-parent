package com.cseu.server.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: CseuAuthenticationEntryPoint
 * @projectName cseu-parent
 * @description: 用来解决匿名用户访问无权限资源时的异常
 * @author: yalong.xue
 * @date 2019-06-2515:23
 */
@Slf4j
@Component
public class CseuAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("==================身份认证失败====================== ");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有访问权限！");

    }
}
