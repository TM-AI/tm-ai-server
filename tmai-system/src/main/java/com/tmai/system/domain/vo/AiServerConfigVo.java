package com.tmai.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;



/**
 * AI服务地址配置视图对象 ai_server_config
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Data
@ExcelIgnoreUnannotated
public class AiServerConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * AI服务Host
     */
    @ExcelProperty(value = "AI服务Host")
    private String host;

    /**
     * AI服务类别：（1：美颜2：不知道）
     */
    @ExcelProperty(value = "AI服务类别：", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=：美颜2：不知道")
    private Long type;

    /**
     * 服务是否可用
     */
    @ExcelProperty(value = "服务是否可用")
    private Boolean usable;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String comment;


}
