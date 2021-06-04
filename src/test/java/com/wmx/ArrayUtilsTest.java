package com.wmx;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * ArrayUtils 通用数组工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/11/22 10:14
 */
public class ArrayUtilsTest {

    /**
     * boolean[] add(final boolean[] array, final boolean element)
     * byte[] add(final byte[] array, final byte element)
     * char[] add(final char[] array, final char element)
     * double[] add(final double[] array, final double element)
     * float[] add(final float[] array, final float element)
     * int[] add(final int[] array, final int element)
     * long[] add(final long[] array, final long element)
     * short[] add(final short[] array, final short element)
     * T[] add(final T[] array, final T element)
     * 复制给定数组并在新数组的末尾添加给定元素。返回新数组，原数组不变。（数组也能像List一样操作）
     * 如果输入数组是{@code null}，则返回一个新的单元素数组，其组件类型与元素相同。
     */
    @Test
    public void add() {
        Integer[] integers = {};
        Integer[] add = ArrayUtils.add(integers, 100);
        add = ArrayUtils.add(add, 100);

        System.out.println(ArrayUtils.toString(integers));//{}
        System.out.println(ArrayUtils.toString(add));//{100,100}
    }

    /**
     * boolean[] addAll(final boolean[] array1, final boolean... array2)
     * byte[] addAll(final byte[] array1, final byte... array2)
     * char[] addAll(final char[] array1, final char... array2)
     * double[] addAll(final double[] array1, final double... array2)
     * float[] addAll(final float[] array1, final float... array2)
     * int[] addAll(final int[] array1, final int... array2)
     * long[] addAll(final long[] array1, final long... array2)
     * short[] addAll(final short[] array1, final short... array2)
     * T[] addAll(final T[] array1, final T... array2)
     * 将给定数组的所有元素添加到新数组中，新数组包含array1的所有元素，以及array2的所有元素。返回新数组，原数组不变。
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     */
    @Test
    public void addAll() {
        Integer[] integers1 = {100, 200, 300};
        Integer[] integers2 = {1, 2, 3, 4, 5};

        Integer[] addAll = ArrayUtils.addAll(integers1, integers2);

        System.out.println(ArrayUtils.toString(integers1));//{100,200,300}
        System.out.println(ArrayUtils.toString(addAll));//{100,200,300,1,2,3,4,5}
    }

    /**
     * boolean[] clone(final boolean[] array)
     * 支持操作8种基本数据类型
     * T[] clone(final T[] array)
     * 克隆数组，如果参数是 null，则返回 null。
     */
    @Test
    public void clone1() {
        Integer[] integers1 = {100, 200, 300};
        Integer[] clone = ArrayUtils.clone(integers1);
        Integer[] add = ArrayUtils.add(clone, 2000);

        System.out.println(ArrayUtils.toString(integers1));//{100,200,300}
        System.out.println(ArrayUtils.toString(add));//{100,200,300,2000}
    }

    @Test
    public void hashCode1() {
        String[] strings = {"100", "100X", "300P"};
        System.out.println(ArrayUtils.getLength(strings));//3
        System.out.println(ArrayUtils.hashCode(strings));//124771894
    }

    /**
     * boolean isSameType(final Object array1, final Object array2)
     * 检查两个数组的元素是否为同一类型。
     */
    @Test
    public void isSameType() {
        String[] strings1 = {"100", "100X", "300P"};
        String[] strings2 = {"20", "10"};
        Integer[] integers = {1, 2, 3, 4, 5};

        boolean sameType1 = ArrayUtils.isSameType(strings1, integers);
        boolean sameType2 = ArrayUtils.isSameType(strings1, strings2);
        System.out.println(sameType1);//false
        System.out.println(sameType2);//true
    }

    /**
     * boolean isSorted(final boolean[] array)
     * 支持操作8种基本数据类型
     * boolean isSorted(final T[] array)
     * 检查提供的数组是否按自然顺序排序。如果 array 为null或者长度小于2，则恒返回 true.
     */
    @Test
    public void isSorted() {
        String[] strings1 = {"aa", "bb", "c"};
        Integer[] integers = {1, 2, 13, 4, 5};
        System.out.println(ArrayUtils.isSorted(strings1));//true
        System.out.println(ArrayUtils.isSorted(integers));//false
    }

    /**
     * int[] removeAllOccurences(final int[] array, final int element)
     * 支持操作8种基本数据类型
     * T[] removeAllOccurences(final T[] array, final T element)
     * 从指定数组中移除指定的所有元素。所有后续元素都向左移动（从索引中减去一个）。
     * 如果数组不包含这样的元素，则不会从数组中移除任何元素。
     * 如果输入数组是<code>null</code>，则返回<code>null</code>
     */
    @Test
    public void removeAllOccurences() {
        String[] strings1 = {"aa", "bb", "c", "aa"};
        String[] strings = ArrayUtils.removeAllOccurences(strings1, "aa");
        System.out.println(ArrayUtils.toString(strings1));//{aa,bb,c,aa}
        System.out.println(ArrayUtils.toString(strings));//{bb,c}
    }

    /**
     * reverse(final double[] array)
     * reverse(final double[] array, final int startIndexInclusive, final int endIndexExclusive)
     * 支持操作8种基本数据类型
     * reverse(final Object[] array)
     * 反转给定数组的顺序,当 array 是null，则不会有任何效果。
     * 在给定范围内[startIndexInclusive,endIndexExclusive]反转给定数组的顺序。
     */
    @Test
    public void reverse() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.reverse(integers);
        System.out.println(ArrayUtils.toString(integers));//{5,4,13,2,1}
    }

    /**
     * shift(final boolean[] array, final int offset)-改变给定数组的顺序。
     * 支持操作8种基本数据类型
     * shift(final Object[] array, int startIndexInclusive, int endIndexExclusive, int offset)
     */
    @Test
    public void shift() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.shift(integers, 2);
        System.out.println(ArrayUtils.toString(integers));//{4,5,1,2,13}
        ArrayUtils.shift(integers, 4);
        System.out.println(ArrayUtils.toString(integers));//{5,1,2,13,4}
    }

    /**
     * shuffle(final byte[] array)
     * 使用Fisher算法随机指定数组的元素顺序。
     * <p>
     * 随机改数组的元素顺序
     * T[] toArray(final T... items)
     * 创建类型安全的泛型数组。这个方法只有在提供相同类型的参数时才有意义，这样编译器才能推断出数组本身的类型。
     */
    @Test
    public void shuffle() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.shuffle(integers);
        System.out.println(ArrayUtils.toString(integers));

        Integer[] toArray = ArrayUtils.toArray(1, 2, 3, 4, 5);
    }

    /**
     * double[] toPrimitive(final Double[] array)
     * double[] toPrimitive(final Double[] array, final double valueForNull)
     * 将包装类型转为基本类型，valueForNull 是当元素为 null 时的默认值，
     * 否则如果存在 null 元素，而又没有设置默认值，则会出现空指针异常。
     */
    @Test
    public void toPrimitive() {
        Integer[] integers = {1, 2, 13, null, 5};
        int[] ints = ArrayUtils.toPrimitive(integers, -1);
        System.out.println(ArrayUtils.toString(ints));//{1,2,13,-1,5}
    }

    /**
     * boolean contains(final Object[] array, final Object objectToFind): 检查 objectToFind 是否在给定数组 array 中
     */
    @Test
    public void test() {
        String[] arrs = {"1", "22", "33", null};
        System.out.println(ArrayUtils.contains(arrs, "1"));//true
        System.out.println(ArrayUtils.contains(arrs, null));//true
        System.out.println(ArrayUtils.contains(arrs, "rt"));//false
        System.out.println(ArrayUtils.contains(null, null));//false
    }


}
