package com.cseu.server.user.controller.rpc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.CseuRole;
import com.cseu.server.user.bean.CseuRolePermission;
import com.cseu.server.user.service.CseuGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.ArrayList;


/**
 * @title: CseuUserRpcController
 * @projectName user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1822:18
 */
@RestController
@RequestMapping("/rpc/u/uc")
@Slf4j
public class CseuUserRpcController implements CseuGuestRpcService {


    @Autowired
    private CseuGuestService cseuGuestService;


    @Autowired
    private Scheduler scheduler;

    @Override
    public Mono<CseuGuest> findCseuUserByUserCount(String userCount) {
        log.info("查找用户信息，用户名{}", userCount);
        //使用自定义的线程池执行会阻塞的任务，防止reactor线程被阻
        return Mono.defer(() -> Mono.justOrEmpty(cseuGuestService.getOne(new QueryWrapper<CseuGuest>().eq("user_count", userCount)))).subscribeOn(scheduler);
    }

    @Override
    public Flux<CseuRole> findCseuRolesByUserId(ArrayList<Long> id) {
        return null;
    }

    @Override
    public Flux<CseuGuest> findCseuGuestByUserId(Long id) {
        return null;
    }

    @Override
    public Flux<CseuRolePermission> findByPermissionIdList(ArrayList<Long> id) {
        return null;
    }

}
