package com.tmai.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * AI服务接口参数配置业务对象 ai_param_config
 *
 * @author ruoyi
 * @date 2023-03-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiParamConfigBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 参数code
     */
    @NotBlank(message = "参数code不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 参数JSON字符串
     */
    @NotBlank(message = "参数JSON字符串不能为空", groups = { AddGroup.class, EditGroup.class })
    private String paramStr;


}
