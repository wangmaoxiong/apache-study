package com.wmx.jdk8;

import java.util.UUID;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 15:33
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getNameById(Integer id) {
        return id + ":" + UUID.randomUUID();
    }
}
