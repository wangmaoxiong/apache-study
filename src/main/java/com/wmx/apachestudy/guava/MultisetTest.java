package com.wmx.apachestudy.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/**
 * {@link Multiset} 接口继承了 {@link Collection} 接口
 * 1、Multiset 相当于 {@link Set}，区别在于 Multiset 可添加相同的元素，它的内部使用一个 HashMap 来维护
 * 2、同理 Multiset 也有自己的实现类：{@link HashMultiset}、{@link LinkedHashMultiset}、{@link TreeMultiset} 等
 * 3、HashMultiset 、TreeMultiset 是无序的，LinkedHashMultiset 是有序的，操作完全同理 HashSet、TreeSet、LinkedHashSet
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 8:54
 */
public class MultisetTest {
    /**
     * HashMultiset 、TreeMultiset 是无序的，LinkedHashMultiset 是有序的
     */
    @Test
    public void hashMultiset() {
        Multiset<Object> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add(200);
        hashMultiset.add("a");
        hashMultiset.add("大汉");
        //[a x 2, 200, 大汉]
        System.out.println(hashMultiset);
        //a a 200 大汉
        hashMultiset.stream().forEach(item -> System.out.print(item+" "));
    }

    /**
     * HashMultiset 、TreeMultiset 是无序的，LinkedHashMultiset 是有序的
     */
    @Test
    public void linkedHashMultiset() {
        LinkedHashMultiset<Object> linkedHashMultiset = LinkedHashMultiset.create();
        linkedHashMultiset.add("中国");
        linkedHashMultiset.add("大秦");
        linkedHashMultiset.add("大汉");
        linkedHashMultiset.add("大秦");
        linkedHashMultiset.add("大唐");

        //[中国, 大秦 x 2, 大汉, 大唐]
        System.out.println(linkedHashMultiset);
        //中国 大秦 大秦 大汉 大唐
        linkedHashMultiset.stream().forEach(item -> System.out.print(item+" "));
    }

    /**
     * HashMultiset 、TreeMultiset 是无序的，LinkedHashMultiset 是有序的
     */
    @Test
    public void treeMultiset() {
        TreeMultiset<Comparable> treeMultiset = TreeMultiset.create();
        treeMultiset.add("中国");
        treeMultiset.add("秦");
        treeMultiset.add("汉");
        treeMultiset.add("秦");
        treeMultiset.add("唐");

        //[中国, 唐, 汉, 秦 x 2]
        System.out.println(treeMultiset);
        //中国 唐 汉 秦 秦
        treeMultiset.stream().forEach(item -> System.out.print(item+" "));
    }


}
