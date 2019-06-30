package com.cseu.server.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cseu_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long cseuRoleId;

    private Long cseuGuestId;

    private Long cseuPermisionId;

    private Date ceateTime;

    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
