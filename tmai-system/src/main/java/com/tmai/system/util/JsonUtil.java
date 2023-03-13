package com.tmai.system.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author Created by DK
 * @Date 2021-05-13 20:11
 **/
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    static {
        // 移除null字段 和 ""字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        // 忽略空bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 统一日期格式yyyy-MM-dd HH:mm:ss
//        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //反序列化忽略字段大小写
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        //反序列化枚举忽略大小写
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    public static ObjectMapper getObjectMapper() {
        return JsonUtil.objectMapper;
    }

    public static <T> String toString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串格式错误");
        }
    }

    public static <T> String toStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串格式错误");
        }
    }

    public static <T> T toObject(String str, Class<T> clazz) {
        if (ObjectUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON字符串格式错误:class[" + clazz.getName() + "],json:[" + str + "]", e);
        }
    }

    public static <T> T toObject(String str, TypeReference<T> typeReference) {
        if (ObjectUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON字符串格式错误:" + str);
        }
    }

    public static <T> T toCollection(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            throw new RuntimeException("JSON字符串格式错误:" + str + "[" + javaType.toString() + "]");
        }
    }

    public static <T> T toList(String str, Class<?>... elementClasses) {
        return toCollection(str, List.class, elementClasses);
    }

    public static  <T> T convertValue(Object obj,Class<T> originClass){
        return objectMapper.convertValue(obj, originClass);
    }
}
