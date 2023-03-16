package com.tmai.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI服务调用日志业务对象 ai_server_log
 *
 * @author ruoyi
 * @date 2023-03-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiServerLogBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * AI服务ID
     */
    @NotNull(message = "AI服务ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long serverId;

    /**
     * AI服务备注
     */
    @NotBlank(message = "AI服务备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverComment;

    /**
     * AI服务URL
     */
    @NotBlank(message = "AI服务URL不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverUrl;

    /**
     * 调用AI服务耗时（/毫秒）
     */
    @NotNull(message = "调用AI服务耗时（/毫秒）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long elapsedTime;

    /**
     * 是否成功
     */
    @NotNull(message = "是否成功不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean success;

    /**
     * 开始调用时间
     */
    @NotNull(message = "开始调用时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date callTime;


}
