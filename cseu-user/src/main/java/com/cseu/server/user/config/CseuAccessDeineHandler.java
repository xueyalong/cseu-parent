package com.cseu.server.user.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: CseuAccessDeineHandler
 * @projectName cseu-parent
 * @description: 用来解决认证过的用户访问无权限资源时的异常
 * @author: yalong.xue
 * @date 2019-06-2721:37
 */
public class CseuAccessDeineHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print("没有访问权限");
    }

}
