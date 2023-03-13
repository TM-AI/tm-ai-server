package com.tmai.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tmai.system.domain.bo.ImgToImgBo;
import com.tmai.system.domain.response.ImgToImgResponse;

/**
 * Created by Tommy Zeng
 * 2023/3/12 12:40
 **/
public interface IBeautyService {
    ImgToImgResponse imgToImg(ImgToImgBo form) throws JsonProcessingException;
}
