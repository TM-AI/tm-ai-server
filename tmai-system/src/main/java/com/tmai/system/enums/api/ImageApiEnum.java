package com.tmai.system.enums.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Created by DK
 * @Date 2023-03-08 19:45
 **/
@Getter
@AllArgsConstructor
public enum ImageApiEnum implements IApi {

    IMG_TO_IMG("/sdapi/v1/img2img","POST","根据图片生成图片"),
    ;
    private final String path;

    private final String httpMethod;

    private final String desc;
}
