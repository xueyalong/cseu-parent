package com.cseu.server.gateway.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @title: Jwt
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-3011:46
 */
public class Jwt {
    private static String key = "cHsGeU0XFsDESIlkJiseFsLsjLS";

    private static String redisTokenKey = "CSEU_AUTH_KEY:";
    private static Long expire_time = 24 * 60 * 60 * 1000L;

    public static String createJwt(String userId) {
        //默认签发有效期24小时的token
        return createJwt(userId, "subject", "issure", expire_time);
    }

    public static String createJwt(String id, String subject, String issure, long till) {
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(key), SignatureAlgorithm.HS256.getJcaName()))
                .setIssuer(issure)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + till));
        return jwtBuilder.compact();
    }

    public static Claims parseJwt(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
        return claims;


    }

    public static String saveToken(RedisTemplate t, String username) {
        final String token = createJwt(username);
        t.opsForValue().set(redisTokenKey + username, token, expire_time);
        return token;
    }
}
