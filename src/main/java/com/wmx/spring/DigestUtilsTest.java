package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Spring-core 核心工具类 之 md5摘要工具类，用于提取摘要
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 11:43
 */
public class DigestUtilsTest {

    /**
     * byte[] md5Digest(byte[] bytes)
     * 1、对字节数组提取 md5 摘要，返回字节数组
     * byte[] md5Digest(InputStream inputStream)
     * 2、对字节输入流提取 md5 摘要，返回字节数组，适合对文件进去提取摘要
     * String md5DigestAsHex(byte[] bytes)
     * 3、对字节数组提取 md5 摘要，以16进制字符串返回
     * String md5DigestAsHex(InputStream inputStream)
     * 4、对字节输入流提取 md5 摘要，以16进制字符串返回，适合对文件进去提取摘要
     */
    @Test
    public void md5DigestAsHex() {
        String pass = "123456ppx";
        String digestAsHex = DigestUtils.md5DigestAsHex(pass.getBytes());
        //2ac22117a86bb88d9d9204cad495d1cc
        System.out.println(digestAsHex);

        try {
            File file = new File("C:\\wmx\\temp\\git\\MyDocument\\images\\cookie.gif");
            String s = DigestUtils.md5DigestAsHex(new FileInputStream(file));
            //a68d24f23f700520f8156cf6914eb1be
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
