package com.tmai.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * AI服务地址配置业务对象 ai_server_config
 *
 * @author ruoyi
 * @date 2023-03-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiServerConfigBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * AI服务Host
     */
    @NotBlank(message = "AI服务Host不能为空", groups = { AddGroup.class, EditGroup.class })
    private String host;

    /**
     * AI服务类别：（1：美颜2：不知道）
     */
    @NotNull(message = "AI服务类别：（1：美颜2：不知道）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long type;


}
