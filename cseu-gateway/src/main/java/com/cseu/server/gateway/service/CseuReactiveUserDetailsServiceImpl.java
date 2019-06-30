package com.cseu.server.gateway.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.gateway.mapper.CseuGuestMapper;
import com.cseu.server.gateway.mapper.GuestRoleMapper;
import com.cseu.server.gateway.mapper.RoleMapper;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.GuestRole;
import com.cseu.server.user.bean.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @title: CseuReactiveUserDetailsServiceImpl
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2819:56
 */
public class CseuReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
    @Override
    public Mono<UserDetails> findByUsername(String userCount) {
        CseuGuest cseuGuests = CseuGuest.builder().build().selectOne(new QueryWrapper<CseuGuest>().eq("user_count", userCount));
        Mono<CseuGuest> cseuGuestMono = Mono.just(cseuGuests);
        if (Objects.nonNull(cseuGuests)) {
            List<GuestRole> guestRoleList = GuestRole.builder().build().selectList(new QueryWrapper<GuestRole>().eq("cseu_guest_id", cseuGuests.getId()));
            List<Long> roleIds = new ArrayList<>();
            List<String> authoritiesList = new ArrayList<>();
            guestRoleList.forEach(guestRole -> {
                roleIds.add(guestRole.getId());
            });
            roleIds.forEach(id->{
               Role role= Role.builder().id(id).build().selectById();
               if(Objects.nonNull(role)){
                   authoritiesList.add(role.getCseuRoleCode());
               }
            });

            UserDetails userDetails = User.builder()
                    .authorities(authoritiesList.toArray(new String[authoritiesList.size()]))
                    .username(cseuGuests.getUserCount())
                    .password(cseuGuests.getPasswd())
                    .roles(authoritiesList.toArray(new String[authoritiesList.size()]))
                    .build();
            return Mono.just(userDetails);

        }

        return Mono.empty();
    }

}
