package com.tmai.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AI服务接口参数配置对象 ai_param_config
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_param_config")
public class AiParamConfig extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 参数code
     */
    private String code;
    /**
     * 参数JSON字符串
     */
    private String paramStr;

}
