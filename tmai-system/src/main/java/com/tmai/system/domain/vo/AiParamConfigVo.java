package com.tmai.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * AI服务接口参数配置视图对象 ai_param_config
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@Data
@ExcelIgnoreUnannotated
public class AiParamConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 参数code
     */
    @ExcelProperty(value = "参数code")
    private String code;

    /**
     * 参数JSON字符串
     */
    @ExcelProperty(value = "参数JSON字符串")
    private String paramStr;


}
