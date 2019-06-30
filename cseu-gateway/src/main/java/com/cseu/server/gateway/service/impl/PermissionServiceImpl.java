package com.cseu.server.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cseu.server.gateway.mapper.PermissionMapper;
import com.cseu.server.gateway.service.PermissionService;
import com.cseu.server.user.bean.Permission;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
