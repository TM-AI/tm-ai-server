package com.tmai.system.domain.bo;

import com.tmai.system.annotation.EnumValidator;
import com.tmai.system.enums.BeautyTypeEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author Created by DK
 * @Date 2023-03-10 18:15
 **/
@Data
public class ImgToImgBo {

    /**
     * 美化类型
     * REAL_MALE:真实风格-男
     * REAL_FEMALE:真实风格-女
     */
    @EnumValidator(enumClass = BeautyTypeEnum.class, message = "必须为『REAL_MALE』或『REAL_FEMALE』")
    private String beautyType = "REAL_FEMALE";

    /**
     * BASE64编码图片字符串
     */
    @NotBlank
    private String imgStr;

    /**
     * 美化程度,可选值[1,30],默认10
     */
    @Min(1)
    @Max(30)
    private Double beautifyLevel=10.0;

    /**
     * 输出图片宽度
     */
    @Max(1024)
    private int width;

    /**
     * 输出图片高度
     */
    @Max(1024)
    private int height;
}
