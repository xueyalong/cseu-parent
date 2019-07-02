package com.cseu.server.gateway.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.gateway.auth.Jwt;
import com.cseu.server.gateway.base.exception.CesuException;
import com.cseu.server.user.bean.CseuGuest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @title: SignInController
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-3004:06
 */

@RestController
@RequestMapping("/auth")
@Slf4j
public class SignInController {


    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/refesh")
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        CseuGuest cseuGuest = CseuGuest.builder().build().selectOne(new QueryWrapper<CseuGuest>().eq("user_count", username));
        if (Objects.nonNull(cseuGuest)) {
            return Jwt.saveToken(redisTemplate, username);
        }
        return CesuException.ErrorEnum.USER_NOT_EXISTS;
    }

    @GetMapping("/token")
    @ResponseBody
    public Object token(@RequestParam("username") String username, @RequestParam("password") String password) {
        CseuGuest cseuGuest = CseuGuest.builder().build().selectOne(new QueryWrapper<CseuGuest>().eq("user_count", username));
        if (Objects.nonNull(cseuGuest)) {
            return Jwt.createJwt(cseuGuest.getUserCount());
        }
        return CesuException.ErrorEnum.USER_NOT_EXISTS;
    }
}