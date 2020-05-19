package com.wmx.apachestudy.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/19 10:33
 */
public class BinaryEncoders {

    public static void main(String[] args) throws DecoderException {
        new BinaryEncoders().test1();
        new BinaryEncoders().test2();
    }

    /**
     * Base64 编解码。
     */
    public void test1() {
        String content = "万里长城今犹在，不见当年秦始皇";
        //Base64 编码
        String encodeBase64 = Base64.encodeBase64String(content.getBytes());
        System.out.println(encodeBase64);
        //Base64 解码
        byte[] decodeBase64 = Base64.decodeBase64(encodeBase64);
        String result = new String(decodeBase64);
        System.out.println(result);
    }

    /**
     * 16 进制转换
     *
     * @throws DecoderException
     */
    public void test2() throws DecoderException {
        System.out.println("-------test2----------./start------");
        String content = "万里长城今犹在，不见当年秦始皇";
        //文本转成 16 进制的字符串
        String hexString = Hex.encodeHexString(content.getBytes());
        //e4b887e9878ce995bfe59f8ee4bb8ae78ab9e59ca8efbc8ce4b88de8a781e5bd93e5b9b4e7a7a6e5a78be79a87
        System.out.println(hexString);

        //将 16 进制的文本转为 10 进制的字节数组，接着即可转为源字符串.
        byte[] decodeHex = Hex.decodeHex(hexString);
        String s = new String(decodeHex);
        //万里长城今犹在，不见当年秦始皇
        System.out.println(s);
        System.out.println("-------test2----------./end------");
    }
}
