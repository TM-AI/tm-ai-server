package com.tmai.system.annotation;


import com.tmai.system.enums.ParamTypeEnum;

import java.lang.annotation.*;

/**
 * 参数类型(请求Ebay时对应的位置)
 * 放在类上时,为全类参数默认参数,注意,只能控制当前类,不涉及父类
 * @Author Created by DK
 * @Date 2022-10-19 14:40
 **/
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamType {
    /**
     * 参数位置
     * @return
     */
    ParamTypeEnum value();

    /**
     * 别名,请求时字段的名称
     * @return
     */
    String alias() default "";
}
