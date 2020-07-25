package com.wmx.guava;

import com.google.common.primitives.Doubles;
import org.junit.Test;

import java.util.List;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/25 17:51
 */
public class DoublesTest {

    /**
     * List<Double> asList(double... backingArray):
     * 返回由指定数组支持的固定大小的列表，类似于 Arrays#asList(Object[])
     * int compare(double a, double b)：比较大小，a>b 返回1，a=b 返回 0，a<b返回-1
     * <p>
     * double[] toArray(Collection<? extends Number> collection)：将集合转为数组
     */
    @Test
    public void test1() {
        List<Double> doubleList = Doubles.asList(12.56, 34546.34, 6778.34, 87.56);
        //[12.56, 34546.34, 6778.34, 87.56]
        System.out.println(doubleList);

        //-1
        System.out.println(Doubles.compare(45.56, 45.65));

        double[] doubles = Doubles.toArray(doubleList);
        //12.56,6778.34
        System.out.println(doubles[0] + "," + doubles[2]);
    }

    /**
     * double[] concat(double[]... arrays)
     * 按顺序返回一个包含源数组中所有值的数组
     */
    @Test
    public void test2() {
        double[] doubles1 = {1.2, 2.2, 3.2, 45.56};
        double[] doubles2 = {22.2, 452.2, 53.22, 45.56};

        double[] concat = Doubles.concat(doubles1, doubles2);
        //[1.2, 2.2, 3.2, 45.56, 22.2, 452.2, 53.22, 45.56]
        System.out.println(Doubles.asList(concat));

    }

    /**
     * boolean contains(double[] array, double target)
     * 如果数组中包含此元素，则返回 true，否则返回 false
     */
    @Test
    public void test3() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        //true
        System.out.println(Doubles.contains(doubles2, 22.20));
        //true
        System.out.println(Doubles.contains(doubles2, 22.2));
        //false
        System.out.println(Doubles.contains(doubles2, 232.2));
    }

    /**
     * int indexOf(double[] array, double target)
     * 返回 target 在 array 中第一次出现的索引,如果没有，则返回 -1
     * int lastIndexOf(double[] array, double target)
     * 返回 target 在 array 中最后一次出现的索引,如果没有，则返回 -1
     */
    @Test
    public void test4() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        //2
        System.out.println(Doubles.indexOf(doubles2, 513.22));
        //-1
        System.out.println(Doubles.indexOf(doubles2, 513.00));
    }

    /**
     * String join(String separator, double... array)
     * 使用指定的字符串将数组中的元素连接起来
     */
    @Test
    public void test5() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        String join = "(" + Doubles.join(",", doubles2) + ")";
        //(22.2,452.2,513.22,45.56)
        System.out.println(join);
    }

    /**
     * double max(double... array)：获取数组中的最大值，数组不能为空，否则抛异常
     * double min(double... array)：获取数组中的最小值，数组不能为空，否则抛异常
     */
    @Test
    public void test6() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        //513.22
        System.out.println(Doubles.max(doubles2));
        //22.2
        System.out.println(Doubles.min(doubles2));
    }

    /**
     * void reverse(double[] array)：反转数组中的元素
     * void reverse(double[] array, int fromIndex, int toIndex) ：反转数组中指定索引范围内的元素
     */
    @Test
    public void test7() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        Doubles.reverse(doubles2);
        //[45.56, 513.22, 452.2, 22.2]
        System.out.println(Doubles.asList(doubles2));
    }

    /**
     * void sortDescending(double[] array)：对数组中的所有元素进行倒序排列
     * sortDescending(double[] array, int fromIndex, int toIndex)：对数组中的指定范围内的元素进行倒序排列
     */
    @Test
    public void test8() {
        double[] doubles2 = {22.2, 452.2, 513.22, 45.56};
        Doubles.sortDescending(doubles2);
        //[513.22, 452.2, 45.56, 22.2]
        System.out.println(Doubles.asList(doubles2));

        //倒序排列后对所有元素进行反转就变成了升序排列
        Doubles.reverse(doubles2);
        //[22.2, 45.56, 452.2, 513.22]
        System.out.println(Doubles.asList(doubles2));
    }
}
