package com.wmx.apachestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangMaoXiong
 */
@SpringBootApplication
public class ApacheStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheStudyApplication.class, args);
    }

    /**
     * 提供一个 RestTemplate 实例交由 Spring 容器管理
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
