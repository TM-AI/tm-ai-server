package com.tmai.system.domain.response;

import com.tmai.system.domain.request.ImgToImgRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Created by DK
 * @Date 2023-03-08 18:27
 **/
@NoArgsConstructor
@Data
public class ImgToImgResponse {


   private List<String> images;

   private ImgToImgRequest parameters;

   private String info;
}
