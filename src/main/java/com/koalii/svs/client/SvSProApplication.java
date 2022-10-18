package com.koalii.svs.client;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.koalii.svs.client.dao")
public class SvSProApplication {

    private static final Logger log = LoggerFactory.getLogger(SvSProApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SvSProApplication.class, args);

        log.info("=====================================SVS启动成功=====================================");
    }
}
