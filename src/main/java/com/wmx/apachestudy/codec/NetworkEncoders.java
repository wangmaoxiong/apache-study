package com.wmx.apachestudy.codec;

import org.apache.commons.codec.net.URLCodec;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLCodec 对 URL 地址进行编码
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/19 19:02
 */
public class NetworkEncoders {
    public static void main(String[] args) {
        try {
            String url = "http://192.168.1.20:80/server/upload/西游 2.mp4";
            URLCodec urlCodec = new URLCodec();
            String encode = urlCodec.encode(url);
            String decode = urlCodec.decode(encode);
            //http%3A%2F%2F192.168.1.20%3A80%2Fserver%2Fupload%2F%E8%A5%BF%E6%B8%B8+2.mp4
            System.out.println("编码后=" + encode);
            System.out.println("解码后=" + decode);


            String encode1 = URLEncoder.encode(url, "UTF-8");
            String decode1 = URLDecoder.decode(encode1, "UTF-8");
            //http%3A%2F%2F192.168.1.20%3A80%2Fserver%2Fupload%2F%E8%A5%BF%E6%B8%B8+2.mp4
            System.out.println("编码后:" + encode1);
            System.out.println("解码后:" + decode1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
