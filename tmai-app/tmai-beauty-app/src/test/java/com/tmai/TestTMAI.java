package com.tmai;

import com.tmai.system.domain.AiServerConfig;
import com.tmai.system.domain.bo.AiParamConfigBo;
import com.tmai.system.domain.request.ImgToImgRequest;
import com.tmai.system.mapper.AiServerConfigMapper;
import com.tmai.system.service.IAiParamConfigService;
import com.tmai.system.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Created by Tommy Zeng
 * 2023/3/12 17:22
 **/

@SpringBootTest // 此注解只能在 springboot 主包下使用 需包含 main 方法与 yml 配置文件
@DisplayName("TAMI测试案例")
public class TestTMAI {

    @Autowired
    private AiServerConfigMapper serverConfigMapper;

    @Autowired
    private IAiParamConfigService paramConfigService;

    @Test
    public void testSaveServerConfig(){
        AiServerConfig serverConfig = new AiServerConfig();
        serverConfig.setHost("http://silenceyese.vicp.net:27860");
        serverConfig.setType(1L);
        serverConfig.setCreateBy("tommy");
        serverConfig.setCreateTime(new Date());

        serverConfigMapper.insert(serverConfig);
    }

    @Test
    public void testSaveParamConfig(){
        AiParamConfigBo paramConfig = new AiParamConfigBo();
        ImgToImgRequest request = ImgToImgRequest.builder()
            .resizeMode(2)
            .samplerName("DPM++ SDE Karras")
            .samplerIndex("DPM++ SDE Karras")
            .steps(30)
            .cfgScale(7)
            .restoreFaces(true)
            .prompt("ultra realistic 8k cg, picture-perfect face, flawless, clean, masterpiece, professional artwork, famous artwork, cinematic lighting, cinematic bloom, perfect face, beautiful face, beautiful eyes, ((perfect female body, narrow waist)), white hair, red eyes, gorgeous queen, royal, divine, goddess, godlike, (royal palace), fantasy, dreamlike, unreal, science fiction, huge breasts, beautiful clothes, lace, lace trim, lace-trimmed legwear, nsfw, breasts out, absurdly long hair, very long hair, (rich:1.4), prestige, luxury, jewelry, diamond, gold, pearl, gem, sapphire, ruby, emerald, intricate detail, delicate pattern, sexy, charming, alluring, seductive, erotic, enchanting, hair ornament, necklace, earrings, bracelet, armlet")
            .negativePrompt("(worst quality, low quality:1.3), simple background, logo, watermark, text")
            .build();
        paramConfig.setParamStr(JsonUtil.toString(request));
        paramConfig.setCode("BEAUTY_WOMAN");
        paramConfig.setCreateBy("tommy");
        paramConfig.setCreateTime(new Date());
        paramConfigService.insertByBo(paramConfig);
    }

}
