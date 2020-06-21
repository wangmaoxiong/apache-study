package com.wmx.jdk8;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 15:34
 */
public class Test {
    public static void main(String[] args) {
        String version = UserService.showVersion();
        //app:v2.0
        System.out.println(version);

        UserService userService = new UserServiceImpl();
        String info = userService.getInfo();
        //Sun Jun 21 15:35:35 CST 2020: OK!
        System.out.println(info);

        //1001:e175355c-761e-4a6e-bf74-0694295297a8
        System.out.println(userService.getNameById(1001));
    }
}
