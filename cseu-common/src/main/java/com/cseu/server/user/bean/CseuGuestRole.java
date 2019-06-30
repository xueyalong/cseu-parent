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

/**
 * <p>
 * 
 * </p>
 *
 * @author yalong.xue
 * @since 2019-06-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("cseu_guest_role")
public class CseuGuestRole extends Model<CseuGuestRole> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("cseu_role_id")
    private Long cseuRoleId;

    @TableField("cseu_guest_id")
    private Long cseuGuestId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("opt_uid")
    private Long optUid;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
