package com.tmai.system.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tmai.system.domain.bo.AiServerLogBo;
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
import com.tmai.system.service.IAiServerLogService;
import com.tmai.system.service.IBeautyService;
import com.tmai.system.util.JsonUtil;
import com.tmai.system.util.ParamTypeResolveUtil;
import com.tmai.system.util.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

/**
 * Created by Tommy Zeng
 * 2023/3/12 09:22
 **/

@RequiredArgsConstructor
@Service
@Slf4j
public class BeautyServiceImpl implements IBeautyService {

    @Autowired
    IAiParamConfigService paramConfigService;

    @Autowired
    IAiServerConfigService serverConfigService;

    @Autowired
    IAiServerLogService serverLogService;

    public ImgToImgResponse imgToImg(ImgToImgBo form) {

        AiParamConfigVo param = paramConfigService.queryByCode(form.getBeautyType());

        AiServerConfigVo server = serverConfigService.getNextServerByType(1L);

        long startTime = System.currentTimeMillis();
        AiServerLogBo serverLog = new AiServerLogBo();
        serverLog.setCallTime(new Date((startTime)));
        serverLog.setServerComment(server.getComment());
        serverLog.setServerUrl(server.getHost());
        serverLog.setServerId(server.getId());

        ImgToImgRequest request = JsonUtil.toObject(param.getParamStr(), ImgToImgRequest.class);
        request.setInitImages(Collections.singletonList(form.getImgStr()));
        request.setWidth(form.getWidth());
        request.setHeight(form.getHeight());
        request.setDenoisingStrength(form.getBeautifyLevel() / 100);

        ImgToImgResponse imgResponse = null;
        try {
            imgResponse = execute(server, request, new TypeReference<ImgToImgResponse>() {
            });

            serverLog.setSuccess(true);
            serverLog.setElapsedTime(System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("调用AI服务失败 => {}", e.getMessage());
            serverLog.setSuccess(false);
            serverLog.setElapsedTime(System.currentTimeMillis() - startTime);
        }finally {
            serverLogService.insertByBo(serverLog);
        }
        return imgResponse;
    }


    private <T> T execute(AiServerConfigVo serverConfigVo, Object request, TypeReference<T> result) {
        RequestParamTypeDTO requestParamTypeDTO = ParamTypeResolveUtil.resolveParam(request);
        return RestTemplateUtil.execute(requestParamTypeDTO, serverConfigVo.getHost() + ((IApi) ImageApiEnum.IMG_TO_IMG).getPath(),
            result, HttpMethod.valueOf(((IApi) ImageApiEnum.IMG_TO_IMG).getHttpMethod()));
    }
}
