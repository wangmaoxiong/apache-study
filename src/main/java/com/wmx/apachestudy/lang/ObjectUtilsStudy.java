package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/6/3 11:19
 */
public class ObjectUtilsStudy {
    /**
     * allNotNull(final Object... values) 检查给定数组中的任何元素值是否都不是 null。
     * anyNotNull(final Object... values) 检查给定数组中的元素是否有不是 null 的值。
     * <p>
     * ObjectUtils.allNotNull(*)             = true
     * ObjectUtils.allNotNull(*, *)          = true
     * ObjectUtils.allNotNull(null)          = false
     * ObjectUtils.allNotNull(null, null)    = false
     * ObjectUtils.allNotNull(null, *)       = false
     * ObjectUtils.allNotNull(*, null)       = false
     * ObjectUtils.allNotNull(*, *, null, *) = false
     * <p>
     * ObjectUtils.anyNotNull(*)                = true
     * ObjectUtils.anyNotNull(*, null)          = true
     * ObjectUtils.anyNotNull(null, *)          = true
     * ObjectUtils.anyNotNull(null, null, *, *) = true
     * ObjectUtils.anyNotNull(null)             = false
     * ObjectUtils.anyNotNull(null, null)       = false
     */
    public void test1() {
        String a = "";
        Float f = null;
        boolean b1 = ObjectUtils.allNotNull(a);
        boolean b2 = ObjectUtils.allNotNull(a, f);
        boolean b3 = ObjectUtils.anyNotNull(a, f);
        //true,false,true
        System.out.println(b1 + "," + b2 + "," + b3);
    }

    /**
     * clone(final T obj) 克隆对象。如果 obj 为  null，则返回 null.
     * cloneIfPossible(final T obj): 先调 clone(final T obj) ，如果返回 null，则返回原来的 obj 对象.
     */
    public void test2() {
        Map<String, Object> dataMap = new HashMap<>(8);
        dataMap.put("code", 200);
        dataMap.put("msg", "success");
        Map<String, Object> clone = ObjectUtils.clone(dataMap);
        Object o = ObjectUtils.cloneIfPossible(null);
        clone.put("code", 1000);
        //{msg=success, code=200}
        System.out.println(dataMap);
        //{msg=success, code=1000}
        System.out.println(clone);
    }

    /**
     * compare(final T c1, final T c2)
     * compare(final T c1, final T c2, final boolean nullGreater)
     * 1、如果 c1 < c2,则返回负数；如果 c1 > c2,则返回正数；如果 c1 = c2,则返回 0；
     * 2、c1、c2 可以为 null。nullGreater 为  false 时，先判断 c1 如果 null ，则返回 -1，再判断 c2 如果为 null，则返回 1.
     * 3、compare(final T c1, final T c2) 底层是 compare(c1, c2, false)
     */
    public void test3() {
        System.out.println(ObjectUtils.compare(2, 3));
        System.out.println(ObjectUtils.compare(2, -3));
        System.out.println(ObjectUtils.compare(2, null));
        System.out.println(ObjectUtils.compare(2, null, true));
        System.out.println(ObjectUtils.compare(null, 3));
        System.out.println(ObjectUtils.compare(null, 3, true));
        System.out.println(ObjectUtils.compare(3, 3));
        System.out.println(ObjectUtils.compare(9.89f, 3.45f));
        System.out.println(ObjectUtils.compare("秦", "汉"));
        System.out.println(ObjectUtils.compare("元", "汉"));
    }

    /**
     * 如果传递的对象是 null，则返回默认值
     * ObjectUtils.defaultIfNull(null, null)      = null
     * ObjectUtils.defaultIfNull(null, "")        = ""
     * ObjectUtils.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtils.defaultIfNull("abc", *)        = "abc"
     * ObjectUtils.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * <p>
     * 返回数组中不是 null 的第一个值
     * ObjectUtils.firstNonNull(null, null)      = null
     * ObjectUtils.firstNonNull(null, "")        = ""
     * ObjectUtils.firstNonNull(null, null, "")  = ""
     * ObjectUtils.firstNonNull(null, "zz")      = "zz"
     * ObjectUtils.firstNonNull("abc", *)        = "abc"
     * ObjectUtils.firstNonNull(null, "xyz", *)  = "xyz"
     * ObjectUtils.firstNonNull(Boolean.TRUE, *) = Boolean.TRUE
     * ObjectUtils.firstNonNull()                = null
     */
    public void test4() {
        String defaultIfNull1 = ObjectUtils.defaultIfNull(null, "[]");
        String defaultIfNull2 = ObjectUtils.defaultIfNull("ha", "");
        System.out.println(defaultIfNull1 + "," + defaultIfNull2);
    }

    /**
     * 检查对象是否为空，支持：CharSequence、Array、Collection、Map
     * <p>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty("")               = true
     * ObjectUtils.isEmpty(" ")              = false
     * ObjectUtils.isEmpty("ab")             = false
     * ObjectUtils.isEmpty(new int[]{})      = true
     * ObjectUtils.isEmpty(new int[]{1,2,3}) = false
     * ObjectUtils.isEmpty(1234)             = false
     * <p>
     * ObjectUtils.isNotEmpty(null)             = false
     * ObjectUtils.isNotEmpty("")               = false
     * ObjectUtils.isNotEmpty("ab")             = true
     * ObjectUtils.isNotEmpty(new int[]{})      = false
     * ObjectUtils.isNotEmpty(new int[]{1,2,3}) = true
     * ObjectUtils.isNotEmpty(1234)             = true
     */

    @Test
    public void isEmpty() {
        System.out.println(ObjectUtils.isEmpty(null));//true
        System.out.println(ObjectUtils.isEmpty(""));//true
        System.out.println(ObjectUtils.isEmpty(" "));//false
        System.out.println(ObjectUtils.isEmpty(new int[]{}));//true
    }

    /**
     * max(final T... values)：获取其中最大的值
     * min(final T... values): 获取其中最小的值
     * median(final T... items)：获取其中的中位数
     */
    public void test5() {
        Integer[] ints = new Integer[]{6, 3, 5, 99, 45, 34, 55, 79, 44, 88, 31};
        Integer max = ObjectUtils.max(ints);
        Integer min = ObjectUtils.min(ints);
        Integer median = ObjectUtils.median(ints);
        System.out.println(max + "," + min + "," + median);
    }

    public static void main(String[] args) {
        ObjectUtilsStudy objectUtilsStudy = new ObjectUtilsStudy();
        objectUtilsStudy.test1();
        objectUtilsStudy.test2();
        objectUtilsStudy.test3();
        objectUtilsStudy.test4();
        objectUtilsStudy.test5();
    }
}
