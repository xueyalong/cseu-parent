package com.cseu.server.user.controller.rpc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.Permission;
import com.cseu.server.user.bean.Role;
import com.cseu.server.user.service.CseuGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;


/**
 * @title: CseuUserRpcController
 * @projectName user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1822:18
 */
@RestController
@RequestMapping("/rpc/u/")
@Slf4j
public class CseuUserRpcController implements CseuGuestRpcService {


    @Autowired
    private CseuGuestService cseuGuestService;


    @Override
    public CseuGuest findCseuUserByUserCount(@RequestParam(value = "userCount") String userCount) {
        log.info("查找用户信息，用户名{}", userCount);
        //使用自定义的线程池执行会阻塞的任务，防止reactor线程被阻
        return cseuGuestService.getOne(new QueryWrapper<CseuGuest>().eq("user_count", userCount));
    }

    @Override
    public Flux<Role> findCseuRolesByUserId(@RequestParam("parames") ArrayList<Long> parames) {
        return null;
    }

    @Override
    public Flux<CseuGuest> findCseuGuestByUserId(@RequestParam(value = "parames") Long parames) {
        return null;
    }

    @Override
    public Flux<Permission> findByPermissionIdList(@RequestParam(value = "parames") ArrayList<Long> parames) {
        return null;
    }

}
