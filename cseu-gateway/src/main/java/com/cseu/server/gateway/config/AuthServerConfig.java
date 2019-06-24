package com.cseu.server.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.cseu.server.gateway.service.impl.UserDetailsServicIpml;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: AuthServerConfig
 * @projectName cseu-user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1521:18
 */
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    // 资源ID
    private static final String SOURCE_ID = "order";
    private static final int ACCESS_TOKEN_TIMER = 60 * 60 * 24;
    private static final int REFRESH_TOKEN_TIMER = 60 * 60 * 24 * 30;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CseuGuestRpcService cseuGuestRpcService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("myapp").resourceIds(SOURCE_ID).authorizedGrantTypes("password", "refresh_token")
                .scopes("all").authorities("ADMIN").secret(new BCryptPasswordEncoder().encode("lxapp")).accessTokenValiditySeconds(ACCESS_TOKEN_TIMER)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(accessTokenConverter());
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }

    // JWT
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             * 重写增强token方法,用于自定义一些token总需要封装的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String userName = authentication.getUserAuthentication().getName();
                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
               CseuGuest guest = cseuGuestRpcService.findCseuUserByUserCount( userName);
                final Map<String, Object> additionalInformation = new HashMap<>();
                additionalInformation.put("guest", JSONObject.toJSON(guest));
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }
        };
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey("SigningKey");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    public UserDetailsServicIpml guestService() {
        return new UserDetailsServicIpml();
    }

}
