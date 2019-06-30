package com.cseu.server.gateway.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cseu.server.gateway.mapper.CseuGuestMapper;
import com.cseu.server.gateway.service.CseuGuestService;
import com.cseu.server.user.bean.CseuGuest;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-30
 */
@Service
public class CseuGuestServiceImpl extends ServiceImpl<CseuGuestMapper, CseuGuest> implements CseuGuestService {

}
