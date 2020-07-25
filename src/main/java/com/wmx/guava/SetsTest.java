package com.wmx.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/25 17:04
 */
public class SetsTest {

    @Test
    public void test1() {
        HashSet<String> hashSet = Sets.newHashSet();
        hashSet.add("中国");
        hashSet.add("朝鲜");
        hashSet.add("古巴");

        //朝鲜 中国 古巴
        hashSet.stream().forEach(item -> System.out.print(item + " "));

        HashSet<String> newHashSet = Sets.newHashSet("中国", "朝鲜", "古巴");
        //朝鲜 中国 古巴
        newHashSet.stream().forEach(item -> System.out.print(item + " "));
    }

    /**
     * SetView<E> difference(final Set<E> set1, final Set<?> set2)
     * 获取 set1 中有，而 set2 中没有的元素，相当于 set1 - set2 的差集，注意返回的集合不可修改，属于不可变对象
     */
    @Test
    public void test2() {
        HashSet<String> newHashSet1 = Sets.newHashSet("中国", "朝鲜", "古巴");
        LinkedHashSet<Object> linkedHashSet = Sets.newLinkedHashSet();
        linkedHashSet.add("中国");
        linkedHashSet.add("美国");
        linkedHashSet.add("德国");

        Sets.SetView<String> difference = Sets.difference(newHashSet1, linkedHashSet);
        UnmodifiableIterator<String> iterator = difference.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            //朝鲜 古巴
            System.out.print(next + " ");
        }
    }

    /**
     * 返回两个集合的交集，返回的集合不可修改。
     */
    @Test
    public void test3() {
        HashSet<String> newHashSet1 = Sets.newHashSet("中国", "朝鲜", "古巴");
        CopyOnWriteArraySet<Object> copyOnWriteArraySet = Sets.newCopyOnWriteArraySet();
        copyOnWriteArraySet.add("美国");
        copyOnWriteArraySet.add("中国");
        copyOnWriteArraySet.add("德国");

        Sets.SetView<String> difference = Sets.intersection(newHashSet1, copyOnWriteArraySet);
        UnmodifiableIterator<String> iterator = difference.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            //中国
            System.out.print(next + " ");
        }
    }

    /**
     * SetView<E> union(final Set<? extends E> set1, final Set<? extends E> set2)
     * 返回两个集合的并集的不可修改的视图
     */
    @Test
    public void test4() {
        TreeSet<String> treeSet = Sets.newTreeSet();
        treeSet.add("秦汉");
        treeSet.add("元明");

        LinkedHashSet<Object> linkedHashSet = Sets.newLinkedHashSet();
        linkedHashSet.addAll(ImmutableSet.of("大秦", "大汉", "元明"));

        Sets.SetView<Object> union = Sets.union(treeSet, linkedHashSet);
        //[元明, 秦汉, 大秦, 大汉]
        System.out.println(union);
    }
}
