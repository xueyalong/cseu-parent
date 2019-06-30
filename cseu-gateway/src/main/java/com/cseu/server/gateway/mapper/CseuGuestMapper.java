package com.cseu.server.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cseu.server.user.bean.CseuGuest;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-16
 */
@Mapper
public interface CseuGuestMapper extends BaseMapper<CseuGuest> {

}
