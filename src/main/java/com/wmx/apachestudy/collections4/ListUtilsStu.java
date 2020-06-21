package com.wmx.apachestudy.collections4;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * ListUtils 工具类练习
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/11 15:45
 */
@SuppressWarnings("all")
public class ListUtilsStu {
    @org.junit.Test
    public void test0() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<String> strList = Arrays.asList("a1", "b2", "c3", "d4", "e5", "f6", "g7", "h9");
        List<List<Integer>> subSets = ListUtils.partition(intList, 3);
        List<List<String>> lists = ListUtils.partition(strList, 4);
        System.out.println(subSets);//[[1, 2, 3], [4, 5, 6], [7, 8]]
        System.out.println(lists);//[[a1, b2, c3, d4], [e5, f6, g7, h9]]
    }

    //static <E> List<E> intersection(final List<? extends E> list1, final List<? extends E> list2)
    //获取 list1 与 list2 的交集。list1、list2 不变。
    @Test
    public void test1() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> intersection = ListUtils.intersection(names1, names2);
        System.out.println(names1 + "," + names2 + "," + intersection);//[张三, 李四],[王五, 张三],[张三]
    }

    //static boolean isEqualList(final Collection<?> list1, final Collection<?> list2)
    //判断两个 list 的元素是否一一对应相等。
    @Test
    public void test2() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names2.add("张三2");
        boolean equalList = ListUtils.isEqualList(names1, names2);
        System.out.println(equalList);//false
    }

    //static <E> List<E> removeAll(final Collection<E> collection, final Collection<?> remove)
    //获取集合 collection 减去集合 remove 后的元素。collection、remove 集合不变。
    @Test
    public void test3() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> removeAll = ListUtils.removeAll(names1, names2);
        System.out.println(names1 + "," + names2 + "," + removeAll);//[张三, 李四],[王五, 张三],[李四]
    }

    //static <E> List<E> subtract(final List<E> list1, final List<? extends E> list2)
    //从 list1 列表中减去 list2 列表中的所有元素，将结果放入新列表
    @Test
    public void test4() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> subtract = ListUtils.subtract(names1, names2);
        System.out.println(names1 + "," + names2 + "," + subtract);//[张三, 李四],[王五, 张三],[李四]
    }

    //static <E> List<E> sum(final List<? extends E> list1, final List<? extends E> list2)
    //获取 list1、list2 集合元素的并集，会过滤掉重复元素，放到新的集合中。
    @Test
    public void test5() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> sum = ListUtils.sum(names1, names2);
        System.out.println(names1 + "," + names2 + "," + sum);//[张三, 李四],[王五, 张三],[李四, 王五, 张三]
    }

    //static <E> List<E> synchronizedList(final List<E> list)。返回由给定列表支持的同步列表，返回列表的迭代器上需要手动同步。
    @Test
    public void test6() {
        List<String> names1 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names1.add("王五");
        List<String> synchronizedList = ListUtils.synchronizedList(names1);
        synchronized (synchronizedList) {
            Iterator<String> iterator = synchronizedList.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + "\t");//张三   李四  王五
            }
        }
    }

    //static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2)
    //返回一个新列表，包含 list1、list2 的所有元素，不会过滤重复元素。
    @Test
    public void test7() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> union = ListUtils.union(names1, names2);
        System.out.println(names1 + "," + names2 + "," + union);//[张三, 李四],[王五, 张三],[张三, 李四, 王五, 张三]
    }

    /**
     * 1、{@link Arrays#asList(java.lang.Object[])} 返回的是 Arrays 的内部类 {@link Arrays.ArrayList}，而并不是 java.util.ArrayList
     * 2、内部类继承关系：private static class ArrayList<E> extends AbstractList<E> extends AbstractCollection<E> implements List<E>
     * 3、Arrays 的内部类 {@link Arrays.ArrayList} 只重写了部分方法，而  add、remove 方法是没有重写的，在 AbstractList 中直接抛的异常 throw new UnsupportedOperationException();
     */
    @Test
    public void asList() {
        //一定要注意 Arrays.asList 返回的 ArrayList 并不是 java.util.ArrayList，而只是 Arrays 的一个内部类，它没有重写 add、remove 方法
        //调用就会抛出异常 UnsupportedOperationException - 不支持的操作
        List<Integer> integerList = Arrays.asList(12, 22, 32, 23);
        integerList.add(999);
    }

}