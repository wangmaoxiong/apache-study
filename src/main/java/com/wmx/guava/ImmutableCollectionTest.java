package com.wmx.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;

/**
 * 演示不可变集合、不可变对象
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 20:07
 */
public class ImmutableCollectionTest {

    /**
     * ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E... others)
     * 返回一个不可变集，其中包含给定元素，减去重复项，按每个元素的顺序排列
     */
    @Test
    public void test1() {
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c", "a", "d", "b");
        //[a, b, c, d]
        System.out.println(immutableSet);
    }

    /**
     * ImmutableSet<E> copyOf(Collection<? extends E> elements) :
     * <E> ImmutableSet<E> copyOf(E[] elements)
     * ImmutableSet<E> copyOf(Iterable<? extends E> elements)
     * ImmutableSet<E> copyOf(Iterator<? extends E> elements)
     * 返回一个不可变集，其中包含{@code elements}中的每一个元素，减去重复项，按每个元素在源集合中出现的顺序排列。
     */
    @Test
    public void test2() {
        HashSet<Integer> hashSet = Sets.newHashSet(34, 56, 345, 767, 80);
        hashSet.add(889);
        //[56, 80, 345, 889, 34, 767]
        System.out.println(hashSet);

        ImmutableSet<Integer> immutableSet = ImmutableSet.copyOf(hashSet);
        //[56, 80, 345, 889, 34, 767]
        System.out.println(immutableSet);

        String[] strings = new String[]{"1h", "3K", "7Y"};
        ImmutableSet<String> immutableSet2 = ImmutableSet.copyOf(strings);
        //[1h, 3K, 7Y]
        System.out.println(immutableSet2);
    }

    /**
     * Builder<E> builder()：返回新生成器, 用于生成不可变集合
     */
    @Test
    public void test3() {
        Set<Object> immutableSet = ImmutableSet.builder()
                .add("大汉").addAll(Arrays.asList("唐", "宋", "元", "明清")).build();
        //[大汉, 唐, 宋, 元, 明清]
        System.out.println(immutableSet);

        String[] strings = immutableSet.toArray(new String[immutableSet.size()]);
        System.out.println(Arrays.asList(strings));
    }

    /**
     * 演示不可变列表
     */
    @Test
    public void testImmutableList1() {
        ImmutableList<Object> immutableList = ImmutableList.builder().add("张无忌", "李世民", "尉迟恭")
                .addAll(Arrays.asList("杨戬", "娜扎")).build();

        //[张无忌, 李世民, 尉迟恭, 杨戬, 娜扎]
        System.out.println(immutableList);
        //尉迟恭
        System.out.println(immutableList.get(2));
    }

    @Test
    public void testImmutableList2() {
        List<Object> immutableList = ImmutableList.builder().add("张无忌", "李世民", "尉迟恭").addAll(Arrays.asList("杨戬", "娜扎")).build();

        Object[] objects = immutableList.toArray();
        System.out.println(Arrays.asList(objects));

        objects[0] = "展昭";
        System.out.println(Arrays.asList(objects));
    }

    /**
     * 演示 不可变 Map
     */
    @Test
    public void testImmutableMap1() {
        ImmutableMap<String, ? extends Serializable> immutableMap = ImmutableMap.of("id", 1000, "name", "张三", "age", 24);

        //{id=1000, name=张三, age=24}
        System.out.println(immutableMap);
        //张三
        System.out.println(immutableMap.get("name"));

        ImmutableMap<Object, Object> objectImmutableMap = ImmutableMap.builder().put("code", 200).put("msg", "success").build();
        //{code=200, msg=success}
        System.out.println(objectImmutableMap);
    }

    /**
     * ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map)：
     * 返回一个不可变映射，其中包含与 map 相同的条目，返回的映射以与原始映射的顺序相同
     */
    @Test
    public void testImmutableMap2() {
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("code", 200);
        tempMap.put("msg", "success");

        Map<String, Object> immutableMap = ImmutableMap.copyOf(tempMap);
        tempMap.put("data", null);

        System.out.println(tempMap);
        System.out.println(immutableMap);
    }
}
