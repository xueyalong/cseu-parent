package com.cseu.server.user.api;

import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.CseuRole;
import com.cseu.server.user.bean.CseuRolePermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;


/**
 * @title: CseuGuestRpcService
 * @projectName cseu-common
 * @description: 用户API接口
 * @author: yalong.xue
 * @date 2019-06-1822:08
 */
@FeignClient(value = "cseu-user", path = "/rpc/u/")
public interface CseuGuestRpcService {
    /**
     * 根据账号查找用户信息
     */
    @GetMapping(value = "uc")
    Mono<CseuGuest> findCseuUserByUserCount(@RequestParam(value = "userCount") String userCount);

    /**
     * 根据用戶id查找角色信息
     */
    @GetMapping(value = "ri")
    Flux<CseuRole> findCseuRolesByUserId(@RequestParam("id") ArrayList<Long> id);

    /**
     * 根据用户Id找找用户信息
     */
    @GetMapping(value = "ui")
    Flux<CseuGuest> findCseuGuestByUserId(@RequestParam("id") Long id);

    /**
     * 根据角色id查找用户权限
     */
    @GetMapping(value = "pi")
    Flux<CseuRolePermission> findByPermissionIdList(@RequestParam("id") ArrayList<Long> id);
}
