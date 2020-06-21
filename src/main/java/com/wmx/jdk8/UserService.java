package com.wmx.jdk8;

import java.util.Date;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 15:30
 */
public interface UserService {

    String VERSION = "v2.0";

    String getNameById(Integer id);

    static String showVersion() {
        return "app:"+VERSION;
    }

    default String getInfo() {
        return new Date() + ":" + " OK!";
    }

}

