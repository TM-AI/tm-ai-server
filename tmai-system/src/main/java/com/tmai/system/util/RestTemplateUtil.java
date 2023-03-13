package com.tmai.system.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tmai.system.domain.bo.RequestParamTypeDTO;
import com.tmai.system.exception.TMAIException;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author Created by DK
 * @Date 2022-11-04 19:05
 **/
public class RestTemplateUtil {

    private final static RestTemplate restTemplate;
    private final static boolean debug = false;

    static {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(60 * 1000);
        factory.setReadTimeout(10 * 60 * 1000);
        restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new MyResponseErrorHandler());

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        //为了支持restTemplate 支持更多的返回值类型
        MappingJackson2HttpMessageConverter mappingConverter = null;
        for (HttpMessageConverter<?> converter : messageConverters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                mappingConverter = (MappingJackson2HttpMessageConverter) converter;
                break;
            }
        }
        if (mappingConverter == null) {
            mappingConverter = new MappingJackson2HttpMessageConverter();
            messageConverters.add(mappingConverter);
        }
        //支持的类型
        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON_UTF8,
                MediaType.APPLICATION_PDF,
        };
        mappingConverter.setSupportedMediaTypes(Arrays.asList(mediaTypes));

        if (debug) {
            // 设置拦截器，答应请求信息，方便Debug
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

//            interceptors.add(new LoggingClientHttpRequestInterceptor());

            restTemplate.setInterceptors(interceptors);
            //提供对传出/传入流的缓冲,可以让响应body多次读取(如果不配置,拦截器读取了Response流,再响应数据时会返回body=null)
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
        }
    }


    /**
     * Post方式请求ebay
     *
     * @param resolveParam 请求参数
     * @param url          相对路径 含占位符
     * @param result       请求结果返回类
     * @return
     */
    public static <T> T execute(RequestParamTypeDTO resolveParam, String url, TypeReference<T> result, HttpMethod method, Object... uriVariables) {
        HttpHeaders headers = setHttpHeaders(resolveParam);
        StringBuilder requestUrl = new StringBuilder(url);
        requestUrl = setUrlParams(resolveParam, requestUrl);
        setQueryParam(resolveParam, requestUrl);
        HttpEntity<Object> httpEntity = setFormData(resolveParam, headers);

        if (httpEntity == null && resolveParam.getBody().size() > 0) {
            httpEntity = new HttpEntity<>(resolveParam.getBody(), headers);
        }

        if (httpEntity == null) {
            httpEntity = new HttpEntity<>(headers);
        }

        try {
            RequestCallback requestCallback = restTemplate.httpEntityCallback(httpEntity, String.class);
            ResponseExtractor<ResponseEntity<String>> responseExtractor = restTemplate.responseEntityExtractor(String.class);
            ResponseEntity<String> response = restTemplate.execute(requestUrl.toString(), method, requestCallback, responseExtractor, uriVariables);
            if (response == null) {
                throw new NullPointerException("No result");
            }
            return handlerResponse(response, result);
        } catch (TMAIException e1) {
            throw e1;
        } catch (Exception e) {
            throw new TMAIException(TMAIException.ErrorCode.REQUEST_ERROR, "请求异常:" + url + ",错误信息为:" + e.getMessage());
        }
    }

    private static HttpEntity<Object> setFormData(RequestParamTypeDTO resolveParam, HttpHeaders headers) {
        if (resolveParam.getFormData().size() > 0) {
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            resolveParam.getFormData().forEach(param::set);
            return new HttpEntity<>(param, headers);

        }
        return null;
    }

    private static void setQueryParam(RequestParamTypeDTO resolveParam, StringBuilder requestUrl) {
        if (resolveParam.getQuery().size() > 0) {
            requestUrl.append("?");
            for (Map.Entry<String, Object> kv : resolveParam.getQuery().entrySet()) {
                requestUrl.append(kv.getKey()).append("=").append(kv.getValue().toString()).append("&");
            }
            requestUrl.deleteCharAt(requestUrl.length() - 1);
        }
    }

    private static StringBuilder setUrlParams(RequestParamTypeDTO resolveParam, StringBuilder requestUrl) {
        if (resolveParam.getUrl().size() > 0) {
            return new StringBuilder(UriComponentsBuilder.fromUriString(requestUrl.toString())
                    .buildAndExpand(resolveParam.getUrl()).toString());
        }
        return requestUrl;
    }

    private static <T> T handlerResponse(ResponseEntity<String> response, TypeReference<T> result) {
        String bodyStr = response.getBody();
        if (response.getStatusCode().is2xxSuccessful()) {
            if (result.getType().getTypeName().equals(byte[].class.getTypeName())) {
                if (bodyStr != null) {
                    return (T) bodyStr.getBytes(StandardCharsets.UTF_8);
                } else {
                    return null;
                }

            }
            return JsonUtil.toObject(bodyStr, result);
        } else {
//            MercadoErrorDTO mercadoErrorDTO = JsonUtil.toObject(bodyStr, MercadoErrorDTO.class);
//            if (mercadoErrorDTO != null && mercadoErrorDTO.getStatus() != null) {
//                throw new MercadoZTException(mercadoErrorDTO.getMessage(), mercadoErrorDTO.getStatus().toString(), JsonUtil.toString(mercadoErrorDTO.getCause()));
//            }
            throw new TMAIException(TMAIException.ErrorCode.REQUEST_ERROR, response.getStatusCode().value() + ":" + bodyStr);
        }
    }

    /**
     * 设置HTTP请求头  并返回HttpHeaders对象
     *
     * @param resolveParam
     * @return
     */
    private static HttpHeaders setHttpHeaders(RequestParamTypeDTO resolveParam) {
        HttpHeaders headers = new HttpHeaders();
        //设置请求格式
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        if (!CollectionUtils.isEmpty(resolveParam.getHeader())) {
            resolveParam.getHeader().forEach(headers::set);
        }
        return headers;
    }

    public static MultiValueMap<String, String> mapToMultiValueMap(Map<String, Object> data) {
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> kv : data.entrySet()) {
            result.add(kv.getKey(), kv.getValue().toString());
        }
        return result;
    }


    private static class MyResponseErrorHandler implements ResponseErrorHandler {

        /**
         * 返回false 不抛异常
         * 由业务方控制是否需要抛出异常
         *
         * @param response 返回对象
         * @return 是否需要抛出异常
         * @throws IOException 允许抛出IO异常
         */
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {

        }
    }

}
