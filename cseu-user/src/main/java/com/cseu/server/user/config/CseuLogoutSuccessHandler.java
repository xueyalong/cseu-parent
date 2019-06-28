package com.cseu.server.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: LogoutSuccessHandler
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2515:25
 */
@Component
@Slf4j
public class CseuLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {


    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info(" =================  成功退出系统 .... ");
        String access_token = httpServletRequest.getParameter("access_token");
        if (access_token != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
            log.info("token =" + oAuth2AccessToken.getValue());
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        log.info("退出系统成功");
    }

}
