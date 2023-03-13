package com.tmai.system.domain.bo;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Created by DK
 * @Date 2022-10-19 16:04
 **/
@Getter
public class RequestParamTypeDTO {

    /**
     * 请求头参数
     */
    private Map<String,String> header=new HashMap<>();

    /**
     * query参数(问号拼接)
     */
    private Map<String,Object> query=new HashMap<>();

    /**
     * 表单传参 与body传参互斥  表单优先
     */
    private Map<String,Object> formData=new HashMap<>();

    /**
     * url路径传参
     */
    private Map<String,Object> url=new HashMap<>();

    /**
     * body传参 与表单传参互斥  表单优先
     */
    private Map<String,Object> body=new HashMap<>();



    public void putHeader(String key,String value) {
        this.header.put(key,value);
    }

    public void putQuery(String key,Object value) {
        this.query.put(key,value);
    }

    public void putUrl(String key,Object value) {
        this.url.put(key,value);
    }

    public void putBody(String key,Object value) {
        this.body.put(key,value);
    }

    public void putFormData(String key,Object value) {
        this.formData.put(key,value);
    }
}
