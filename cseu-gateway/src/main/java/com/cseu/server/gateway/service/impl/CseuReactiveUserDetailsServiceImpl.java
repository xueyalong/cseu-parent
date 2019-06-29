package com.cseu.server.gateway.service.impl;

import com.cseu.server.gateway.base.exception.CesuException;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.CseuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @title: CseuReactiveUserDetailsServiceImpl
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2819:56
 */
public class CseuReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private CseuGuestRpcService cseuGuestRpcService;

    @Override
    public Mono<UserDetails> findByUsername(String userCount) {
        CseuGuest cseuGuest=cseuGuestRpcService.findCseuUserByUserCount(userCount);
        Mono<CseuGuest> cseuGuestMono = Mono.just(cseuGuest);

        return cseuGuestMono.switchIfEmpty(Mono.error(()->new CesuException(CesuException.ErrorEnum.USER_NOT_EXISTS))
                .flatMap(cseuGuest.getId()-> {
                    return (Mono<UserDetails>) cseuGuestRpcService.findCseuRolesByUserId(cseuGuest.getId());
                }));
    }

}
