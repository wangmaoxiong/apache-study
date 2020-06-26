package com.wmx.apachestudy.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * CommandLineRunner 接口在应用程序已启动、上下文已刷新之后，且在 SpringApplication.run(…) 完成之前进行调用。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/26 17:25
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner run 方法执行," + Arrays.toString(args));
        String forObject = restTemplate.getForObject("https://api.apiopen.top/getJoke?page=2&count=1&type=video", String.class);
        System.out.println(forObject);
    }
}
