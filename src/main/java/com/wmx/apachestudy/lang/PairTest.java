package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.junit.Test;

import java.util.*;

/**
 * 当一个方法需要返回两个及以上字段时，一般会封装成一个临时对象返回，现在有了 Pair 和 Triple 就不需要了
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/6/13 9:02
 */
@SuppressWarnings("all")
public class PairTest {

    /**
     * ImmutablePair：不可变对象对，里面有 left、right 两个元素，用于封装两个对象。
     * ImmutablePair 是不可变的，构建对象后，无法再 setValue、setLeft、setRight 修改值
     * 但是其中的两个元素对象是可变的。
     * 如果两个配对对象都是线程安全的，则是线程安全的。
     */
    @Test
    public void immutablePair() {
        Map<String, Object> map = new HashMap(2);
        map.put("code", 200);
        map.put("msg", "success");

        List<String> ids = new ArrayList<>();
        ids.add("11L");
        ids.add("12L");
        ids.add("13L");

        ImmutablePair<Map, List<String>> immutablePair = ImmutablePair.of(map, ids);
        Map<String, Object> pairLeft = immutablePair.getLeft();
        List<String> pairRight = immutablePair.getRight();
        pairRight.add("44A");

        System.out.println(pairLeft);//{msg=success, code=200}
        System.out.println(pairRight);//[11L, 12L, 13L, 44A]
    }

    /**
     * MutablePair：可变对象对，里面有 left、right 两个元素，用于封装两个对象。
     * MutablePair 是可变的，构建对象后，可以再 setValue、setLeft、setRight 修改值
     */
    @Test
    public void mutablePair() {
        Map<String, Object> map = new HashMap(2);
        map.put("code", 200);
        map.put("msg", "success");

        List<String> ids = new ArrayList<>();
        ids.add("11L");
        ids.add("12L");
        ids.add("13L");

        MutablePair<Map, List<String>> mutablePair = MutablePair.of(map, ids);
        Map<String, Object> pairLeft = mutablePair.getLeft();
        List<String> pairRight = mutablePair.getRight();
        pairRight.add("44A");

        System.out.println(pairLeft);//{msg=success, code=200}
        System.out.println(pairRight);//[11L, 12L, 13L, 44A]
    }

    /**
     * ImmutableTriple：不可变三元对象组，里面有 left、middle、right 三个元素，用于封装三个对象。
     * ImmutableTriple 是不可变的，构建对象后，无法再 setXxx 修改值
     * 但是其中的三个元素对象是可变的。
     * 如果其中的元素对象都是线程安全的，则是线程安全的。
     */
    @Test
    public void immutableTriple() {
        // 同时封装 3 个对象
        ImmutableTriple<Integer, String, Date> immutableTriple = ImmutableTriple.of(112, "蚩尤后裔", new Date());
        //112,蚩尤后裔,Sun Jun 13 09:24:37 CST 2021
        System.out.println(immutableTriple.getLeft() + "," + immutableTriple.getMiddle() + "," + immutableTriple.getRight());
    }

    /**
     * MutableTriple：可变三元对象组，里面有 left、middle、right 三个元素，用于封装三个对象。
     * MutableTriple 是可变的，构建对象后，可以再 setXxx 修改值
     * 如果其中的元素对象都是线程安全的，则是线程安全的。
     */
    @Test
    public void mutableTriple() {
        // 同时封装 3 个对象
        MutableTriple<Integer, String, Date> mutableTriple = MutableTriple.of(212, "蚩尤后裔", new Date());
        mutableTriple.setLeft(800);
        //800,蚩尤后裔,Sun Jun 13 09:27:37 CST 2021
        System.out.println(mutableTriple.getLeft() + "," + mutableTriple.getMiddle() + "," + mutableTriple.getRight());
    }

}
