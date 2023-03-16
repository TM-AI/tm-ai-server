package com.tmai.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * AI服务调用日志视图对象 ai_server_log
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Data
@ExcelIgnoreUnannotated
public class AiServerLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * AI服务ID
     */
    @ExcelProperty(value = "AI服务ID")
    private Long serverId;

    /**
     * AI服务备注
     */
    @ExcelProperty(value = "AI服务备注")
    private String serverComment;

    /**
     * AI服务URL
     */
    @ExcelProperty(value = "AI服务URL")
    private String serverUrl;

    /**
     * 调用AI服务耗时（/毫秒）
     */
    @ExcelProperty(value = "调用AI服务耗时", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "/=毫秒")
    private Long elapsedTime;

    /**
     * 是否成功
     */
    @ExcelProperty(value = "是否成功")
    private Boolean success;

    /**
     * 开始调用时间
     */
    @ExcelProperty(value = "开始调用时间")
    private Date callTime;


}
