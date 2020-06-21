package com.wmx.spring;

import org.springframework.util.Base64Utils;

/**
 * Spring-core 核心工具类 之 Base64 工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 12:23
 */
public class Base64UtilsTest {

    /**
     * 解码：
     * byte[] decode(byte[] src)
     * byte[] decodeFromString(String src)
     * byte[] decodeFromUrlSafeString(String src)
     * byte[] decodeUrlSafe(byte[] src)
     * 编码：
     * byte[] encode(byte[] src)
     * String encodeToString(byte[] src)
     * String encodeToUrlSafeString(byte[] src)
     * byte[] encodeUrlSafe(byte[] src)
     *
     * @param args
     */
    public static void main(String[] args) {
        String pass = "123456ppx";
        String encodeToString = Base64Utils.encodeToString(pass.getBytes());
        byte[] decodeFromString = Base64Utils.decodeFromString(encodeToString);

        //源内容：123456ppx
        System.out.println("源内容：" + pass);
        //编码后：MTIzNDU2cHB4
        System.out.println("编码后：" + encodeToString);
        //解码后：123456ppx
        System.out.println("解码后：" + new String(decodeFromString));
    }
}
