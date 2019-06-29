package com.cseu.server.user.api;

import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.CseuRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @title: CseuGuestRpcService
 * @projectName cseu-common
 * @description: 用户API接口
 * @author: yalong.xue
 * @date 2019-06-1822:08
 */
@FeignClient(value = "cseu-user",path = "/rpc/u/")
public interface CseuGuestRpcService  {
    @GetMapping(value = "d")
    Mono<CseuGuest> findCseuUserByUserCount(@RequestParam(value = "userCount") String userCount);
    @GetMapping(value = "d")
    Flux<CseuRole> findCseuRolesByUserId(@RequestParam("id") long id);
}
