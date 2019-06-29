package com.cseu.server.gateway.service.impl;

import com.cseu.server.gateway.base.exception.CesuException;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

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
        Mono<CseuGuest> cseuGuestMono=cseuGuestRpcService.findCseuUserByUserCount(userCount);

        return cseuGuestMono.switchIfEmpty(Mono.error(()->new CesuException(CesuException.ErrorEnum.USER_NOT_EXISTS)))
                .flatMap(cseuGuest-> cseuGuestRpcService.findCseuGuestByUserId(cseuGuest.getId())
                        .collect(ArrayList<Long>::new,(cseuRolesIdList,cseuRole)->cseuRolesIdList.add(cseuRole.getId()))
                ).flatMap(cseuRolesIdList -> cseuGuestRpcService.findCseuRolesByUserId(cseuRolesIdList)
                        .collect(ArrayList<Long>::new, (permissionIdList, cseuRolePermission) -> permissionIdList.add(cseuRolePermission.getId()))
                        .flatMap(permissionIdList -> cseuGuestRpcService.findByPermissionIdList(permissionIdList)
                                .collect(ArrayList<SimpleGrantedAuthority>::new, (authorityList, permission) -> authorityList.add(new SimpleGrantedAuthority(permission.getCode())))
                        )
                        .zipWith(cseuGuestMono, (authorityList, cseuGuest) -> new User(cseuGuest.getUserCount(), cseuGuest.getPasswd(), authorityList)));
    }

}
