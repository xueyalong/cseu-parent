package com.cseu.server.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("cseu_guest")
public class CseuGuest extends Model<CseuGuest> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id	
     */
    private Long id;

    /**
     * 登录名
     */
    @TableField("user_count")
    private String userCount;

    /**
     * 真是姓名
     */
    @TableField("real_name")
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
    @TableField("nick_name")
    private String nickName;

    /**
     * 注册时间
     */
    @TableField("reg_time")
    private Date regTime;

    /**
     * 修改时间
     */
    @TableField("mod_time")
    private Date modTime;

    /**
     * 用户头像
     */
    @TableField("head_url")
    private String headUrl;

    /**
     * 用户qq
     */
    @TableField("user_qq")
    private String userQq;

    @TableField("user_wchat")
    private String userWchat;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 乐观锁version字段
     */
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
