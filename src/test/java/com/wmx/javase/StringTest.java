package com.wmx.javase;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * java.lang.String API 测试类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/12/24 19:04
 */
public class StringTest {

    /**
     * String 类型基本知识
     */
    @Test
    public void test1() {
        String s = "Hello";//jvm看到 "Hello ",在string池创建对象存储它，并将其引用返回给 s
        //jvm看到 "Java"，在string池创建新的string对象存储它，再把新建的string对象的引用返回给s。
        //而原先的 "Hello "仍然在string池内没有消失，等待垃圾回收，Hello 是不能被修改的。
        s = "Java";

        //因为常量池中已经有 Hello 对象，所以此时 s1 引用会直接指向它
        String s1 = "Hello";
        //首先 jvm 会为 xyz 字符串在 String 常量池中创建一个对象
        //然后 new String 又会在堆内存中创建对象（非常量池），所以一共是两个对象
        String x = new String("xyz");
    }

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
        System.out.println(Long.MAX_VALUE);
        System.out.println("9223372036854775807".length());
        System.out.println(new Date().getTime());
    }

    /**
     * static String format(String format, Object... args)	使用指定的格式字符串和参数返回格式化的字符串。
     */
    @Test
    public void format() {
        // "%.2f", %：表示小数点前任意位数、2：表示两位小数、f：格式后的结果表示浮点型
        // 此时参数必须是浮点型、如果是其它类型则报错
        System.out.println(String.format("%.2f", 34.55666F));//34.56
        System.out.println(String.format("%.2f", 34.5));//34.50
    }

}
