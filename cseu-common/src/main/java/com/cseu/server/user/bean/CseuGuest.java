package com.cseu.server.user.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CseuGuest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id	
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 登录名
     */
    private String userCount;
    /**
     * 真是姓名
     */
    private String realName;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 邮件
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称

     */
    private String nickName;
    /**
     * 注册时间
     */
    private Date regTime;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 用户头像
     */
    private String headUrl;
    /**
     * 用户qq
     */
    private String userQq;
    private String userWchat;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 乐观锁version字段
     */
    private Integer version;


}
