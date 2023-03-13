package com.tmai.system.enums;

/**
 * @Author Created by DK
 * @Date 2022-10-19 14:42
 **/
public enum ParamTypeEnum {
    /**
     * url 问号传参
     */
    QUERY,

    /**
     * url 占位符传参
     */
    URL,

    /**
     * 表单传参
     */
    FORM_DATA,

    /**
     * body
     */
    BODY,

    /**
     * 请求头
     */
    HEADER,

    /**
     * 不参与请求
     */
    NOT_REQUEST,
    ;
}
