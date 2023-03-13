package com.tmai.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AI服务地址配置对象 ai_server_config
 *
 * @author ruoyi
 * @date 2023-03-12
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

}
