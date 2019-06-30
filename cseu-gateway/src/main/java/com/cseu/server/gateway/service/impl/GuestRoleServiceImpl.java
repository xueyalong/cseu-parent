package com.cseu.server.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cseu.server.gateway.mapper.GuestRoleMapper;
import com.cseu.server.gateway.service.GuestRoleService;
import com.cseu.server.user.bean.GuestRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-30
 */
@Service
public class GuestRoleServiceImpl extends ServiceImpl<GuestRoleMapper, GuestRole> implements GuestRoleService {

}
