package com.wmx.http;

import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/11/22 18:38
 */
public class URLEncodedUtilsTest {

    @Test
    public void f() {
        try {
            String url = "http://192.168.1.20:80/server/upload/西游2 仙履奇缘.mp4";
            String encode = URLEncoder.encode(url, "UTF-8");
            String decode = URLDecoder.decode(url, "UTF-8");

            System.out.println("源地址：" + url);
            System.out.println("编码后：" + encode);
            System.out.println("解码后：" + decode);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
