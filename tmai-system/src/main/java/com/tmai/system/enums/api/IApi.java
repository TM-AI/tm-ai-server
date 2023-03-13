package com.tmai.system.enums.api;

/**
 * @Author Created by DK
 * @Date 2023-01-11 16:50
 **/
public interface IApi {

    String getPath();

    /**
     * GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
     * @link org.springframework.http.HttpMethod
     */
    String getHttpMethod();
}
