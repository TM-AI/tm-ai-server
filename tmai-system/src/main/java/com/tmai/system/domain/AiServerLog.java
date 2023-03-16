package com.tmai.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * AI服务调用日志对象 ai_server_log
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_server_log")
public class AiServerLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * AI服务ID
     */
    private Long serverId;
    /**
     * AI服务备注
     */
    private String serverComment;
    /**
     * AI服务URL
     */
    private String serverUrl;
    /**
     * 调用AI服务耗时（/毫秒）
     */
    private Long elapsedTime;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 开始调用时间
     */
    private Date callTime;

}
