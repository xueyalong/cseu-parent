package com.cseu.server.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.service.CseuGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title: UserDetailsServicIpm
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2319:39
 */
@Slf4j
public class UserDetailsServicIpml implements UserDetailsService {

    @Resource
    private CseuGuestService cseuGuesService;

    @Override
    public UserDetails loadUserByUsername(String userCount) throws UsernameNotFoundException {
        log.info("用户进入认证了，userCount={}", userCount);
        CseuGuest guest = cseuGuesService.getOne(new QueryWrapper<CseuGuest>().eq("user_count", userCount));
        if (guest == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        log.info("用户信息是:{}", JSON.toJSONString(guest));
        return new User(guest.getUserCount(), guest.getPasswd(), AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
