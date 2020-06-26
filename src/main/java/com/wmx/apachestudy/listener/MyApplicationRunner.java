package com.wmx.apachestudy.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * ApplicationRunner 接口在 在应用程序已启动、上下文已刷新之后，且在 SpringApplication.run(…) 完成之前进行调用。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/26 17:23
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner run 方法执行," + args.getOptionNames());
        String forObject = restTemplate.getForObject("https://api.apiopen.top/getJoke?page=3&count=1&type=video", String.class);
        System.out.println(forObject);
    }
}
