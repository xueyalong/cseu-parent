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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cseu_guest_role")
public class GuestRole extends Model<GuestRole> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long cseuRoleId;

    private Long cseuGuestId;

    private Date createTime;

    private Long optUid;

    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
