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
@TableName("cseu_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CseuRole extends Model<CseuRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    @TableField("cseu_role_name")
    private String cseuRoleName;

    /**
     * 角色编码
     */
    @TableField("cseu_role_code")
    private String cseuRoleCode;

    @TableField("creata_time")
    private LocalDateTime creataTime;

    @TableField("updata_time")
    private LocalDateTime updataTime;

    @TableField("cseu_guest_id")
    private Long cseuGuestId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
