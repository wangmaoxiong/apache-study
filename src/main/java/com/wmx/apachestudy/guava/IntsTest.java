package com.wmx.apachestudy.guava;


import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * guava 提供了 Bytes/Shorts/Ints/Longs/Floats/Doubles/Chars/Booleans 这些基本数据类型的扩展支持
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 11:12
 */
public class IntsTest {
    /**
     * List<Integer> asList(int... backingArray：返回由指定数组支持的固定大小的列表，不可修改，类似 {@link Arrays#asList(Object[])}
     * int max(int... array): 获取数组中的最大值，数据为空或者为null，都会抛异常
     * int min(int... array)：获取数组中的最大值，数据为空或者为null，都会抛异常
     * void sortDescending(int[] array)：按降序对{@code array}的元素排序，array 为 null 时抛出异常.
     * void reverse(int[] array)：反转数组中的元素，array 为 null 时抛出异常.
     */
    @Test
    public void ints1() {
        //将 int 数组转为 Integer 型 list：[1, 2, 4, 5, 6]
        List<Integer> integerList = Ints.asList(1, 2, 4, 5, 6);
        System.out.println(integerList);

        int[] ints = new int[]{3, 2, 4, 55, 34, 33, 55, 67};
        //取 int 数组中的最大值、最小值：67、2
        int max = Ints.max(ints);
        int min = Ints.min(ints);
        System.out.printf("%s、%s%n", max, min);

        //对 ints 中的元素降序排列：[67, 55, 55, 34, 33, 4, 3, 2]
        Ints.sortDescending(ints);
        System.out.println(Ints.asList(ints));

        //对数组中的元素首尾颠倒：[67, 55, 33, 34, 55, 4, 2, 3]
        ints = new int[]{3, 2, 4, 55, 66, 34, 33, 55, 67};
        Ints.reverse(ints);
        System.out.println(Ints.asList(ints));
    }

    /**
     * String join(String separator, int... array)
     * 1、返回一个字符串，该字符串包含由 separator 分隔的 array 元素的值
     * 2、separator 只对数组元素的中间进行连接，不包括开头或结尾
     * 3、array 为 null 时抛异常，为空时，返回 ""
     */
    @Test
    public void ints2() {
        int[] ints2 = new int[]{3, 2, 4, 55, 34, 33, 55, 67};
        String join = Ints.join("','", ints2);
        join = "('" + join + "')";
        //join=('3','2','4','55','34','33','55','67')
        System.out.println("join=" + join);
    }

    /**
     * int[] toArray(Collection<? extends Number> collection)
     * 1、返回一个数组，其中包含 collection 中的每个值，并以 {@link Number#intValue} 的方式转换为 int 值。
     * 2、它和 Collection.toArray() 一样是线程安全的
     * boolean contains(int[] array, int target)
     * 1、如果 target 作为元素出现在 array 中，则返回 true，array 可以为空，但是不能为 null
     */
    @Test
    public void ints3() {
        ArrayList<Integer> arrayList = Lists.newArrayList(100, 200, 400);
        int[] array = Ints.toArray(arrayList);
        //true
        System.out.println(Ints.contains(array, 200));
        //[100, 200, 400]
        System.out.println(Ints.asList(array));
    }

    /**
     * int[] concat(int[]... arrays)：按顺序将多个数组合并成一个新的数组
     */
    @Test
    public void ints4() {
        int[] array1 = Ints.toArray(Ints.asList(1, 2, 4, 56, 345, 23));
        int[] array2 = Ints.toArray(Ints.asList(21, 32, 14, 56, 645, 53));

        int[] concat = Ints.concat(array1, array2);
        //[1, 2, 4, 56, 345, 23, 21, 32, 14, 56, 645, 53]
        System.out.println(Ints.asList(concat));
    }

    /**
     * int compare(int a, int b)：对比 a、b的大小，a>b返回1，a=b返回0，a<b返回-1
     * int indexOf(int[] array, int target) ：查询指定元素的索引位置，array 不能为 null.
     * int lastIndexOf(int[] array, int target)：
     */
    @Test
    public void ints5() {
        List<Integer> integerList = Arrays.asList(124, 23, 25, 22, 11, 23, 46, 88);
        int[] ints = Ints.toArray(integerList);
        int indexOf1 = Ints.indexOf(ints, 23);
        int indexOf2 = Ints.indexOf(ints, 123);
        //1
        System.out.println(indexOf1);
        //-1
        System.out.println(indexOf2);
        //5
        int lastIndexOf = Ints.lastIndexOf(ints, 23);
        System.out.println(lastIndexOf);

        //-1
        System.out.println(Ints.compare(3, 5));
        //0
        System.out.println(Ints.compare(5, 5));
        //1
        System.out.println(Ints.compare(15, 5));
    }
}