package com.tmai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * Created by Tommy Zeng
 * 2023/3/12 20:25
 **/

@SpringBootApplication
@MapperScan("com.tmai.system.mapper")
public class BeautyAppApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication application = new SpringApplication(BeautyAppApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  BeautyAppApplication启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
