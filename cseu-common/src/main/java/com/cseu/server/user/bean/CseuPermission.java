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
@TableName("cseu_permission")
public class CseuPermission extends Model<CseuPermission> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("cseu_role_id")
    private Long cseuRoleId;

    @TableField("cseu_guest_id")
    private Long cseuGuestId;

    @TableField("cseu_permision_id")
    private Long cseuPermisionId;

    @TableField("ceate_time")
    private LocalDateTime ceateTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
