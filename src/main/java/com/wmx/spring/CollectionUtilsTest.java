package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Spring-core 核心工具类 之 集合工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 11:54
 */
public class CollectionUtilsTest {
    /**
     * boolean contains(@Nullable Enumeration<?> enumeration, Object element)
     * boolean contains(@Nullable Iterator<?> iterator, Object element)
     * 1、检查 enumeration、iterator 中是否含有指定的元素，底层使用 {@link ObjectUtils#nullSafeEquals(java.lang.Object, java.lang.Object)}
     * 2、如果 iterator 为 null，则直接返回 false
     */
    @Test
    public void contains() {
        List<Integer> integerList = Arrays.asList(19, 23, 44, 54, 36, 67);
        Iterator<Integer> iterator = integerList.iterator();
        boolean contains1 = CollectionUtils.contains(iterator, "67");

        iterator = integerList.iterator();
        boolean contains2 = CollectionUtils.contains(iterator, Integer.valueOf(67));
        //false, true
        System.out.println(contains1 + ", " + contains2);
    }

    /**
     * Class<?> findCommonElementType(Collection<?> collection)
     * 1、获取集合中公共的元素类型，集合为null或者为空时，返回 null
     * 2、集合中元素如果有多种类型，则返回 null，会遍历其中某一个元素
     */
    @Test
    public void findCommonElementType() {
        List<Integer> integerList = Arrays.asList(19, 23, 44, 54, 36, 67);
        Class<?> elementType = CollectionUtils.findCommonElementType(integerList);
        //class java.lang.Integer
        System.out.println(elementType);

        List<Object> objectList = new ArrayList<>();
        objectList.add(100);
        objectList.add("满分");
        //null
        System.out.println(CollectionUtils.findCommonElementType(objectList));
    }

    /**
     * boolean isEmpty(@Nullable Collection<?> collection)
     * boolean isEmpty(@Nullable Map<?, ?> map)
     * 1、判断集合或者map是否为null或者为空，源码：(collection == null || collection.isEmpty())
     */
    @Test
    public void isEmpty() {
        List<Object> objectList = Arrays.asList();
        Map map = null;
        //true  true
        System.out.println(CollectionUtils.isEmpty(objectList));
        System.out.println(CollectionUtils.isEmpty(map));
    }

    /**
     * void mergeArrayIntoCollection(@Nullable Object array, Collection<E> collection)
     * 1、将数组元素添加到集合中
     * void mergePropertiesIntoMap(@Nullable Properties props, Map<K, V> map)
     * 1、将属性文件（props）中的值添加/提取到 map 中
     */
    @Test
    public void mergeArrayIntoCollection() {
        String[] strings = new String[]{"刻苦", "幸苦", "离开"};

        List<String> stringList = new LinkedList<>();
        stringList.addAll(Arrays.asList("例可", "模拟"));

        CollectionUtils.mergeArrayIntoCollection(strings, stringList);
        //[例可, 模拟, 刻苦, 幸苦, 离开]
        System.out.println(stringList);
    }
}
