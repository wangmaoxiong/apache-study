package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 字符串工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/12 15:34
 */
@SuppressWarnings("all")
public class StringUtilsTest {

    /**
     * boolean isNumeric(final CharSequence cs)：检查 cs 是否只包含 Unicode 数字，小数点不是 Unicode 数字，返回 false
     * 该方法不允许前导符号，无论是正号还是负号
     * * StringUtils.isNumeric(null)   = false
     * * StringUtils.isNumeric("")     = false
     * * StringUtils.isNumeric("  ")   = false
     * * StringUtils.isNumeric("123")  = true
     * * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
     * * StringUtils.isNumeric("12 3") = false
     * * StringUtils.isNumeric("ab2c") = false
     * * StringUtils.isNumeric("12-3") = false
     * * StringUtils.isNumeric("12.3") = false
     * * StringUtils.isNumeric("-123") = false
     * * StringUtils.isNumeric("+123") = false
     */
    @Test
    public void testIsNumeric() {
        System.out.println(StringUtils.isNumeric("00389238920823"));//true
    }

    /**
     * void testSubstring()：从指定的字符串中获取子字符串以避免异常,负数可用于从字符串结尾开始/结束
     * * <pre>
     *  * StringUtils.substring(null, *, *)    = null
     *  * StringUtils.substring("", * ,  *)    = "";
     *  * StringUtils.substring("abc", 0, 2)   = "ab"
     *  * StringUtils.substring("abc", 2, 0)   = ""
     *  * StringUtils.substring("abc", 2, 4)   = "c"
     *  * StringUtils.substring("abc", 4, 6)   = ""
     *  * StringUtils.substring("abc", 2, 2)   = ""
     *  * StringUtils.substring("abc", -2, -1) = "b"
     *  * StringUtils.substring("abc", -4, 2)  = "ab"
     *  * </pre>
     */
    @Test
    public void testSubstring() {
        System.out.println(StringUtils.substring("430000", 0, 2));
        System.out.println(StringUtils.substring("4", 0, 2));
        System.out.println(StringUtils.substring("", 0, 2));
        System.out.println(StringUtils.substring(null, 0, 2));
    }

    /**
     * String abbreviate(final String str, final int maxWidth)：
     * 对 str 字符串进行省略号缩写，maxWidth 表示长度，必须大于等于 4
     */
    @Test
    public void test1() {
        System.out.println(StringUtils.abbreviate("abcdefg", 7));//= "abcdefg"
        System.out.println(StringUtils.abbreviate("abcdefg", 8));//= "abcdefg"
        System.out.println(StringUtils.abbreviate("abcdefg", 4));//= "a..."
        System.out.println(StringUtils.abbreviate("a", 3));// = a..
        System.out.println(StringUtils.abbreviate("abcdefg", 3));// = IllegalArgumentException
    }

    @Test
    public void test2(){
    	String s = "因对方已经调入，[刘凤兰] 无法再撤销变动！ 证件号[530721198608213670] 的人员在本单位下已经存在，无法再撤销到上一版本！";
        System.out.println(s.length());
    }


}
