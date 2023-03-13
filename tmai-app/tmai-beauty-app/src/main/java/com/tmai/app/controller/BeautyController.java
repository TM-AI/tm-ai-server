package com.tmai.app.controller;

import com.ruoyi.common.core.domain.R;
import com.tmai.system.domain.bo.ImgToImgBo;
import com.tmai.system.domain.response.ImgToImgResponse;
import com.tmai.system.service.IBeautyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author Created by DK
 * @Date 2023-03-08 18:32
 **/
@RestController
@RequestMapping("/imageHandler")
public class BeautyController {

    @Autowired
    private IBeautyService beautyService;

    /**
     * 美化图片
     */
    @PostMapping("/beautifyImg")
    public R<ImgToImgResponse> beautifyImg(@RequestBody @Validated ImgToImgBo request) throws IOException {
        ImgToImgResponse imgToImgResponse = beautyService.imgToImg(request);
//        File file = new File("C:\\Users\\Administrator\\Desktop\\test.jpg");
//        if(file.exists()){
//            file.delete();
//        }
//        file.createNewFile();
//        byte[] imageByte = Base64.getDecoder().decode(imgToImgResponse.getImages().get(0));
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(imageByte);
//        outputStream.flush();
//        outputStream.close();
        return R.ok(imgToImgResponse);
    }
}
