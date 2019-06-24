package com.cseu.server.gateway.service.impl;

import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @title: UserDetailsServicIpm
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2319:39
 */
public class UserDetailsServicIpml implements UserDetailsService {

    @Autowired
    private CseuGuestRpcService cseuGuestRpcService;
    @Override
    public UserDetails loadUserByUsername(String userCount) throws UsernameNotFoundException {
        CseuGuest guest=cseuGuestRpcService.findCseuUserByUserCount(userCount);
        User user=new User(guest.getUserCount(),guest.getPasswd(), AuthorityUtils.createAuthorityList("USERS"));
        return user;
    }
}
