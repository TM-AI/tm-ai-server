package com.tmai.system.util;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmai.system.annotation.ParamType;
import com.tmai.system.domain.bo.RequestParamTypeDTO;
import com.tmai.system.enums.ParamTypeEnum;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Created by DK
 * @Date 2022-12-20 18:24
 **/
public class ParamTypeResolveUtil {


    /**
     * 解析请求参数
     * 过滤所有实际内容为空的参数，如空字符串，空列表等
     * @param param
     * @return
     */
    public static RequestParamTypeDTO resolveParam(Object param) {
        RequestParamTypeDTO requestParamTypeDTO = new RequestParamTypeDTO();
        if (param == null) {
            return requestParamTypeDTO;
        }
        try {
            List<Field> fields = getAllField(param.getClass());
            for (Field field : fields) {
                ParamType paramType = getFieldParamType(field);
                if (paramType == null || paramType.value() == ParamTypeEnum.NOT_REQUEST) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(param);
                if (ObjectUtil.isEmpty(value)) {
                    continue;
                }
                //优先级最低
                String key = field.getName();
                //序列化别名次之
                JsonProperty annotation = field.getAnnotation(JsonProperty.class);
                if (annotation != null) {
                    key = annotation.value();
                }
                //自定义注解最高
                if (!ObjectUtils.isEmpty(paramType.alias())) {
                    key = paramType.alias();
                }

                switch (paramType.value()) {
                    case QUERY:
                        requestParamTypeDTO.putQuery(key, value);
                        break;
                    case URL:
                        requestParamTypeDTO.putUrl(key, value);
                        break;
                    case BODY:
                        requestParamTypeDTO.putBody(key, value);
                        break;
                    case HEADER:
                        requestParamTypeDTO.putHeader(key, JsonUtil.toString(value));
                        break;
                    case FORM_DATA:
                        requestParamTypeDTO.putFormData(key, JsonUtil.toString(value));
                        break;
                }
            }
            return requestParamTypeDTO;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("解析封装入参异常", e);
        }
    }


    private static ParamType getFieldParamType(Field field) {
        ParamType annotation = field.getAnnotation(ParamType.class);
        if (annotation == null) {
            annotation = field.getDeclaringClass().getAnnotation(ParamType.class);
        }
        return annotation;
    }

    private static List<Field> getAllField(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();

        while (clazz != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList;
    }

}
