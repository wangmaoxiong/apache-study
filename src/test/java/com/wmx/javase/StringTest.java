package com.wmx.javase;

import org.junit.Test;

import java.util.Arrays;

/**
 * java.lang.String API 测试类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/12/24 19:04
 */
public class StringTest {

    /**
     * replace(CharSequence target, CharSequence replacement)
     * 将与字面目标序列匹配的字符串的每个子字符串替换为指定的字面替换序列。
     */
    @Test
    public void replaceTest() {
        String s = "万里'长城'！";
        String replace = s.replace("'", "");
        System.out.println(s);//万里'长城'！
        System.out.println(replace);//万里长城！
    }

    /**
     * replaceAll(String regex, String replacement)
     * 用给定的字符串替换与给定的正则表达式匹配的此字符串的每个子字符串
     */
    @Test
    public void replaceAllTest() {
        String s = "万里'长城'！";
        String replace = s.replaceAll("['！]", "");
        System.out.println(s);//万里'长城'！
        System.out.println(replace);//万里长城
    }

    /**
     * replaceFirst(String regex, String replacement)
     * 用给定的替换替换与给定的 regular expression匹配的此字符串的第一个子字符串。
     */
    @Test
    public void replaceFirstAllTest() {
        String s = "万里'长城'！";
        String replace = s.replaceFirst("['！]", "");
        System.out.println(s);//万里'长城'！
        System.out.println(replace);//万里长城'！
    }

    /**
     * split(String regex) 将此字符串分割为给定的 regular expression的匹配。
     */
    @Test
    public void split() {
        String s = "万里'长城'！长又长";
        String[] replace = s.split("['！]");
        System.out.println(Arrays.asList(replace));//[万里, 长城, , 长又长]
    }

    /**
     * split(String regex, int limit) 将这个字符串拆分为给定的 regular expression的匹配。
     */
    @Test
    public void split2() {
        String s = "万里'长城'！长又长";
        String[] replace = s.split("['！]", 2);
        System.out.println(Arrays.asList(replace));//[万里, 长城'！长又长]
    }
}
