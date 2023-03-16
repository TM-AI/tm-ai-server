package com.tmai.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI服务地址配置对象 ai_server_config
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_server_config")
public class AiServerConfig extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * AI服务Host
     */
    private String host;
    /**
     * AI服务类别：（1：美颜2：不知道）
     */
    private Long type;
    /**
     * 服务是否可用
     */
    private Boolean usable;
    /**
     * 备注
     */
    private String comment;

}
