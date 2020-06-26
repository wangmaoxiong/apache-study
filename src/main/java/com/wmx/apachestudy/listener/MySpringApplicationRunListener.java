package com.wmx.apachestudy.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.client.RestTemplate;

/**
 * 实现 SpringApplicationRunListener 监听器接口，监听 Spring Boot 的启动流程。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/26 15:31
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    /**
     * 必须提供带 SpringApplication application, String[] args 参数的构造器，否则启动失败抛出异常.
     *
     * @param application
     * @param args ：应用启动类上 main 方法传入的参数
     */
    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        System.out.println(" MySpringApplicationRunListener 构造器执行...");
    }

    @Override
    public void starting() {
        System.out.println(" starting 方法执行...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println(" environmentPrepared 方法执行...");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(" contextPrepared 方法执行...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(" contextLoaded 方法执行...");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(" started 方法执行...");
        //从 Spring 容器中获取指定的 bean，然后执行操作.
        RestTemplate restTemplate = context.getBeanFactory().getBean(RestTemplate.class);
        String forObject = restTemplate.getForObject("https://api.apiopen.top/getJoke?page=1&count=2&type=video", String.class);
        System.out.printf("%s%n", forObject);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println(" running 方法执行...");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(" failed 方法执行...");
    }
}
