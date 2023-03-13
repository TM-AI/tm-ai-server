package com.tmai.system.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmai.system.annotation.ParamType;
import com.tmai.system.enums.ParamTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Created by DK
 * @Date 2023-03-08 18:25
 **/

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImgToImgRequest {

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("init_images")
    private List<String> initImages;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("resize_mode")
    private Integer resizeMode;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("denoising_strength")
    private Double denoisingStrength;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("image_cfg_scale")
    private Integer imageCfgScale;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("mask")
    private String mask;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("mask_blur")
    private Integer maskBlur;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("inpainting_fill")
    private Integer inpaintingFill;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("inpaint_full_res")
    private Boolean inpaintFullRes;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("inpaint_full_res_padding")
    private Integer inpaintFullResPadding;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("inpainting_mask_invert")
    private Integer inpaintingMaskInvert;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("initial_noise_multiplier")
    private Integer initialNoiseMultiplier;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("prompt")
    private String prompt;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("styles")
    private List<String> styles;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("seed")
    private Integer seed;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("subseed")
    private Integer subseed;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("subseed_strength")
    private Integer subseedStrength;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("seed_resize_from_h")
    private Integer seedResizeFromH;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("seed_resize_from_w")
    private Integer seedResizeFromW;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("sampler_name")
    private String samplerName;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("batch_size")
    private Integer batchSize;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("n_iter")
    private Integer nIter;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("steps")
    private Integer steps;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("cfg_scale")
    private Integer cfgScale;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("width")
    private Integer width;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("height")
    private Integer height;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("restore_faces")
    private Boolean restoreFaces;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("tiling")
    private Boolean tiling;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("negative_prompt")
    private String negativePrompt;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("eta")
    private Integer eta;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("s_churn")
    private Integer sChurn;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("s_tmax")
    private Integer sTmax;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("s_tmin")
    private Integer sTmin;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("s_noise")
    private Integer sNoise;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("override_settings")
    private Object overrideSettings;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("override_settings_restore_afterwards")
    private Boolean overrideSettingsRestoreAfterwards;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("script_args")
    private List<Object> scriptArgs;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("sampler_index")
    private String samplerIndex;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("include_init_images")
    private Boolean includeInitImages;

    @ParamType(ParamTypeEnum.BODY)
    @JsonProperty("script_name")
    private String scriptName;

}
