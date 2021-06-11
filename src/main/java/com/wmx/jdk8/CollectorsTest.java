package com.wmx.jdk8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java.util.stream.Collectors :一个有实现{@link Collector} 有用的工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/6/11 21:54
 */
@SuppressWarnings("all")
public class CollectorsTest {

    /**
     * java.util.stream.Collectors :一个有实现{@link Collector} 有用的工具类
     * toList(): 流转 List，还有 toSet()、toMap 等
     */
    @org.junit.Test
    public void collectors1() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Set<String> collect = list.stream().collect(Collectors.toSet());
        //a b c d e
        collect.stream().forEach(item -> System.out.print(item + " "));
    }

    /**
     * Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
     * 1、对元流中的元素进行分组，值相同的视作为一组
     */
    @org.junit.Test
    public void collectors2() {
        Map<Integer, List<Integer>> integerListMap = Stream.of(1, 3, 23, 45, 32, 12, 44, 45, 32, 3, 3).collect(Collectors.groupingBy(item -> item));
        //{32=[32, 32], 1=[1], 3=[3, 3, 3], 23=[23], 44=[44], 12=[12], 45=[45, 45]}
        System.out.println(integerListMap);
    }

    /**
     * Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
     * 1、对元流中的元素进行分组
     */
    @Test
    public void collectors3() {
        List<Map<String, Object>> dataList = this.getDataList();
        /**
         * [{c21=新增, address=深圳市, agency_code=201025},
         * {c21=既往, address=长沙市, agency_code=002015},
         * {c21=删除, address=武汉市, agency_code=304100},
         * {c21=既往, address=深圳市, agency_code=324100}]
         */
        System.out.println(dataList);

        // 根据 c21 的值进行分组，c21 值相同的元素作为一组
        Map<Object, List<Map<String, Object>>> objectListMap = dataList.stream().collect(Collectors.groupingBy(item -> item.get("c21")));
        /**
         * {既往=[{c21=既往, address=长沙市, agency_code=002015}, {c21=既往, address=深圳市, agency_code=324100}],
         * 新增=[{c21=新增, address=深圳市, agency_code=201025}],
         * 删除=[{c21=删除, address=武汉市, agency_code=304100}]}
         */
        System.out.println(objectListMap);
    }

    /**
     * Collector<CharSequence, ?, String> joining(CharSequence delimiter)：将元素按遇到顺序 用指定的分隔符 连接起来
     */
    @Test
    public void collectors4() {
        List<String> stringList = Arrays.asList("12H", "43D", "78G", null, "909I");
        String collect = "'" + stringList.stream().collect(Collectors.joining("','")) + "'";
        //'12H','43D','78G','null','909I'
        System.out.println(collect);
    }

    /**
     * Collector<CharSequence, ?, String> joining(CharSequence delimiter,CharSequence prefix,CharSequence suffix)
     * 将元素按遇到顺序 用指定的分隔符 连接起来，并指定开头与结尾的连接符
     */
    @Test
    public void collectors5() {
        List<String> stringList = Arrays.asList("121H", "431D", "728G", null, "9309I");
        String collect = stringList.stream().collect(Collectors.joining("','", "'", "'"));
        //'121H','431D','728G','null','9309I'
        System.out.println(collect);
    }


    private List<Map<String, Object>> getDataList() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(8);
        Map<String, Object> map2 = new HashMap<>(8);
        Map<String, Object> map3 = new HashMap<>(8);
        Map<String, Object> map4 = new HashMap<>(8);

        map1.put("c21", "新增");
        map1.put("agency_code", "201025");
        map1.put("address", "深圳市");

        map2.put("c21", "既往");
        map2.put("agency_code", "002015");
        map2.put("address", "长沙市");

        map3.put("c21", "删除");
        map3.put("agency_code", "304100");
        map3.put("address", "武汉市");

        map4.put("c21", "既往");
        map4.put("agency_code", "324100");
        map4.put("address", "深圳市");

        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
        dataList.add(map4);

        return dataList;
    }

}
