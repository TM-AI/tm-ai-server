package com.tmai.system.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tmai.system.domain.bo.ImgToImgBo;
import com.tmai.system.domain.bo.RequestParamTypeDTO;
import com.tmai.system.domain.request.ImgToImgRequest;
import com.tmai.system.domain.response.ImgToImgResponse;
import com.tmai.system.domain.vo.AiParamConfigVo;
import com.tmai.system.domain.vo.AiServerConfigVo;
import com.tmai.system.enums.api.IApi;
import com.tmai.system.enums.api.ImageApiEnum;
import com.tmai.system.service.IAiParamConfigService;
import com.tmai.system.service.IAiServerConfigService;
import com.tmai.system.service.IBeautyService;
import com.tmai.system.util.JsonUtil;
import com.tmai.system.util.ParamTypeResolveUtil;
import com.tmai.system.util.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Tommy Zeng
 * 2023/3/12 09:22
 **/

@RequiredArgsConstructor
@Service
public class BeautyServiceImpl implements IBeautyService {

    @Autowired
    IAiParamConfigService paramConfigService;

    @Autowired
    IAiServerConfigService serverConfigService;

    public ImgToImgResponse imgToImg(ImgToImgBo form) {

        AiParamConfigVo paramConfigVo = paramConfigService.queryByCode("BEAUTY_WOMAN");
        AiServerConfigVo serverConfigVo = serverConfigService.getNextServerByType(1L);

        ImgToImgRequest request = JsonUtil.toObject(paramConfigVo.getParamStr(), ImgToImgRequest.class);
        request.setInitImages(Collections.singletonList(form.getImgStr()));
        request.setWidth(form.getWidth());
        request.setHeight(form.getHeight());
        request.setDenoisingStrength(form.getBeautifyLevel() / 100);

        //RequestParamTypeDTO requestParamTypeDTO = ParamTypeResolveUtil.resolveParam(request);
        //String url = serverConfigVo.getHost() + ImageApiEnum.IMG_TO_IMG.getPath();

        //return RestTemplateUtil.execute(
        //    requestParamTypeDTO,
        //    url,
        //    new TypeReference<ImgToImgResponse>(),
        //    ImageApiEnum.IMG_TO_IMG.getHttpMethod());

        return execute(serverConfigVo, request, new TypeReference<ImgToImgResponse>() {
        });
    }


    private <T> T execute(AiServerConfigVo serverConfigVo, Object request, TypeReference<T> result) {
        RequestParamTypeDTO requestParamTypeDTO = ParamTypeResolveUtil.resolveParam(request);
        return RestTemplateUtil.execute(requestParamTypeDTO, serverConfigVo.getHost() + ((IApi) ImageApiEnum.IMG_TO_IMG).getPath(),
            result, HttpMethod.valueOf(((IApi) ImageApiEnum.IMG_TO_IMG).getHttpMethod()));
    }
}
