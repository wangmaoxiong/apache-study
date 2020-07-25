package com.wmx.guava;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/25 20:58
 */
public class StringsTest {

    /**
     * String nullToEmpty(@Nullable String string)
     * 如果给定字符串非空，则返回该字符串；否则返回空字符串。
     */
    @Test
    public void test1() {
        //|
        System.out.println(Strings.nullToEmpty("") + "|");
        // |
        System.out.println(Strings.nullToEmpty(" ") + "|");
        //|
        System.out.println(Strings.nullToEmpty(null) + "|");
        //汪茂雄|
        System.out.println(Strings.nullToEmpty("汪茂雄") + "|");
    }

    /**
     * String emptyToNull(@Nullable String string)
     * 如果给定字符串非空，则返回该字符串；否则返回{@code null}。
     */
    @Test
    public void test2() {
        //null|
        System.out.println(Strings.emptyToNull("") + "|");
        // |
        System.out.println(Strings.emptyToNull(" ") + "|");
        //null|
        System.out.println(Strings.emptyToNull(null) + "|");
        //汪茂雄|
        System.out.println(Strings.emptyToNull("汪茂雄") + "|");
    }

    /**
     * boolean isNullOrEmpty(@Nullable String string)
     * 如果给定字符串为null或为空字符串，则返回{@code true}。
     */
    @Test
    public void test3() {
        //true
        System.out.println(Strings.isNullOrEmpty(""));
        //false
        System.out.println(Strings.isNullOrEmpty(" "));
        //true
        System.out.println(Strings.isNullOrEmpty(null));
        //false
        System.out.println(Strings.isNullOrEmpty("汪茂雄"));
    }

    /**
     * String repeat(String string, int count)
     * 将 string 重复 count 次返回，string 不能为空，count 不能为负数
     */
    @Test
    public void test4() {
        //蚩尤后裔,蚩尤后裔,蚩尤后裔,蚩尤后裔,蚩尤后裔,
        System.out.println(Strings.repeat("蚩尤后裔,", 5));

    }
}
