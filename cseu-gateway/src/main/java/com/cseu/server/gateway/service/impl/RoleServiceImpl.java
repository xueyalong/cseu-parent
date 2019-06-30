package com.cseu.server.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cseu.server.gateway.mapper.RoleMapper;
import com.cseu.server.gateway.service.RoleService;
import com.cseu.server.user.bean.Role;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
