package com.cseu.server.user.controller.rpc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cseu.server.user.api.CseuGuestRpcService;
import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.bean.CseuRole;
import com.cseu.server.user.service.CseuGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: CseuUserRpcController
 * @projectName user
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-1822:18
 */
@RestController
@RequestMapping("/rpc/u/uc")
public class CseuUserRpcController  implements CseuGuestRpcService {
    @Autowired
    private CseuGuestService cseuGuestService;
    @Override
    public CseuGuest findCseuUserByUserCount(String userCount) {
        return cseuGuestService.getOne(new QueryWrapper<CseuGuest>().eq("user_count",userCount));
    }

    @Override
    public List<CseuRole> findCseuRolesByUserId(long id) {
        return null;
    }
}
