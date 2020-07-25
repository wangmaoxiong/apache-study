package com.wmx.guava;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {@link Lists} API 学习
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 8:52
 */
public class ListsTest {
    /**
     * static <E> List<E> asList(@Nullable E first, E[] rest)
     * 1、返回一个不可修改的列表，其中包含指定的第一个元素，并由指定的附加元素数组（rest）支持。
     * static <E> List<E> asList(@Nullable E first, @Nullable E second, E[] rest)
     * 1、返回一个不可修改的列表，其中包含指定的第一个和第二个元素，并由指定的附加元素数组（rest）支持。
     * 2、rest 数组可以为空，但是不能为 null。
     */
    @Test
    public void asList() {
        List<String> list1 = Lists.asList("admin", new String[]{"蚩尤后裔", "admin"});
        List<String> list2 = Lists.asList("admin", "root", new String[]{});
        //[admin, 蚩尤后裔, admin]
        System.out.println(list1);
        //[admin, root]
        System.out.println(list2);
    }

    /**
     * <E> ArrayList<E> newArrayList(E... elements)
     * 1、创建包含给定元素的可变的 ArrayList 实例，使用此方法的唯一原因是以后需要添加或删除元素，否则对于非空元素，请改用 ImmutableList#of() 或 ImmutableList#copyOf(Object[])
     * 2、如果任何元素可能为 null，或者需要对 List#set(int，Object) 的支持，请使用 Arrays#asList.
     * 3、请注意，即使确实需要添加或删除的功能，此方法也只是创建一个空列表然后调用 addAll 方法添加元素，这种方法实际上不是很有用，将来可能会被弃用。
     * <E> ArrayList<E> newArrayList()
     * 1、创建一个可变的空的 ArrayList 实例，如果不需要可变性，请改用 ImmutableList#of()
     * 2、注意对于 Java7 和更高版本，这个方法现在是不必要的，应该被视为不推荐使用的方法，相反直接使用 ArrayList() 的构造函数.
     * ArrayList<E> newArrayList(Iterable<? extends E> elements
     * 1、创建包含给定元素的可变的 ArrayList 实例
     * ArrayList<E> newArrayList(Iterator<? extends E> elements)
     * 1、创建包含给定元素的可变的 ArrayList 实例
     * <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize)
     * 1、创建由具有指定初始大小的数组支持的 ArrayList 实例；
     * 2、注意：对于 Java7 和更高版本，这个方法现在是不必要的，应该被视为不推荐使用的方法
     */
    @Test
    public void newArrayList() {
        List<String> list1 = Lists.newArrayList("admin", "蚩尤后裔", "admin");
        list1.add("炎黄");
        //[admin, 蚩尤后裔, admin, 炎黄]
        System.out.println(list1);
    }

    /**
     * LinkedList<E> newLinkedList()
     * 1、创建一个可变的空的 LinkedList 实例
     * 2、对于 Java7 和更高版本，这个方法现在是不必要的，应该被视为不推荐使用的方法，相反可以直接使用 LinkedList 的构造函数
     * LinkedList<E> newLinkedList(Iterable<? extends E> elements)
     * 1、创建一个包含给定元素的可变的 {@link LinkedList} 实例
     */
    @Test
    public void newLinkedList() {
        LinkedList<String> linkedList = Lists.newLinkedList();
        linkedList.add("中国");
        linkedList.add("大秦");
        //[中国, 大秦]
        System.out.println(linkedList);
    }

    /**
     * List<List<B>> cartesianProduct(List<? extends B>... lists)
     * 1、获取多个 list 的笛卡儿积，返回的 list 不可修改，否则异常 UnsupportedOperationException
     */
    @Test
    public void cartesianProduct() {
        List<Object> list1 = Lists.newArrayList("admin", "root");
        list1.add("蚩尤后裔");
        List<Object> list2 = Lists.newArrayList(100, 200);
        List<List<Object>> cartesianProduct = Lists.cartesianProduct(list1, list2);
        //[[admin, 100], [admin, 200], [root, 100], [root, 200], [蚩尤后裔, 100], [蚩尤后裔, 200]]
        System.out.println(cartesianProduct);
    }

    /**
     * CopyOnWriteArrayList<E> newCopyOnWriteArrayList()
     * 1、创建一个空的 CopyOnWriteArrayList 实例，CopyOnWriteArrayList 是线程安全的。
     * CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> elements)
     * 1、创建包含给定元素的 CopyOnWriteArrayList 实例。
     */
    @Test
    public void newCopyOnWriteArrayList() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = Lists.newCopyOnWriteArrayList();
        copyOnWriteArrayList.add("scott");
        copyOnWriteArrayList.add("蚩尤后裔");
        //[scott, 蚩尤后裔]
        System.out.println(copyOnWriteArrayList);
    }

    /**
     * List<T> reverse(List<T> list)
     * 1、元素顺序反转
     */
    @Test
    public void reverse() {
        List<String> list1 = Lists.newArrayList("admin", "socket", "root");
        List<String> reverse = Lists.reverse(list1);
        //[root, socket, admin]
        System.out.println(reverse);
    }

    /**
     * List<List<T>> partition(List<T> list, int size)
     * 1、分割列表，返回列表的连续子列表，每个子列表的大小相同（个数为 size），最后一个列表个数可能小于 size
     * 2、内部列表都是按原始顺序排列
     */
    @org.junit.Test
    public void partition() {
        List<Integer> intList = Lists.newArrayList(10, 20, 30, 40, 50, 60, 70, 80);
        List<String> strList = Lists.newArrayList("a1", "b2", "c3", "d4", "e5", "f6", "g7", "h8");
        List<List<Integer>> subSets = Lists.partition(intList, 5);
        List<List<String>> listList = Lists.partition(strList, 3);
        //[[10, 20, 30, 40, 50], [60, 70, 80]]
        System.out.println(subSets);
        //[[a1, b2, c3], [d4, e5, f6], [g7, h8]]
        System.out.println(listList);
    }

    /**
     * List<Character> charactersOf(CharSequence sequence)
     * ImmutableList<Character> charactersOf(String string)
     * 1、将字符序列分割成单个字符的列表
     */
    @Test
    public void charactersOf() {
        ImmutableList<Character> characters = Lists.charactersOf("蚩尤后裔");
        List<Character> characters1 = Lists.charactersOf(new StringBuffer("水泊梁山，替天行道。"));
        //[蚩, 尤, 后, 裔]
        System.out.println(characters);
        //[水, 泊, 梁, 山, ，, 替, 天, 行, 道, 。]
        System.out.println(characters1);
    }
}