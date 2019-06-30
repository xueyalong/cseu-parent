package com.cseu.server.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
@TableName("cseu_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String cseuRoleName;

    /**
     * 角色编码
     */
    private String cseuRoleCode;

    private Date creataTime;

    private Date updataTime;

    private Long cseuGuetId;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
