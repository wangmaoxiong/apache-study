package com.wmx.jdk8;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java.util.Stream 表示能应用在一组元素上一次执行的操作序列。Stream 操作分为中间操作或者最终操作两种，
 * 最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样就可以将多个操作依次串起来。Stream 的创建需要指定一个数据源，
 * 比如 java.util.Collection的子类，List或者Set， Map不支持。Stream的操作可以串行stream()执行或者并行parallelStream()执行。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/10 20:00
 */
public class StreamTest {

    /**
     * forEach(Consumer<? super T> action) 遍历
     */
    public void stu1() {
        List<String> list = Arrays.asList("长沙", "深圳", "武汉", "伊犁", "洛阳", "开封");

        // 方式一：增强 for 循环
        for (String item : list) {
            System.out.print(item + "\t");
        }
        System.out.println("---------------------------");

        // 方式二：JDK 1.8 java.util.stream.Stream.forEach(Consumer<? super T> action)
        list.stream().forEach(item -> System.out.print(item + "\t"));
    }

    /**
     * Stream<T> filter(Predicate<? super T> predicate) 过滤
     */
    public void stu2() {
        List<Integer> integerList = Arrays.asList(200, 140, 450, 554, 98, 889);
        integerList.stream().filter(integer -> integer > 300).forEach(integer -> System.out.print(integer + "\t"));
    }

    public void stu3() {
        List<String> list = Arrays.asList("how", "are", "you", "how", "old", "are", "you", "?");
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.print(item + "\t"));
    }

    public void stu4() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);

        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        List<List<Integer>> collect = Stream.of(a, b).collect(Collectors.toList());
        // [[1, 2, 3], [4, 5, 6]]
        System.out.println(collect);

        // 将多个集合中的元素合并成一个集合
        List<Integer> mergeList = Stream.of(a, b).flatMap(list -> list.stream()).collect(Collectors.toList());
        // [1, 2, 3, 4, 5, 6]
        System.out.println(mergeList);

        // 通过Builder模式来构建
        Stream<Object> stream = Stream.builder().add("hello").add("hi").add("byebye").build();
        List<Object> objectList = stream.collect(Collectors.toList());
        System.out.println(objectList);
    }

    public void stu5() {
        List<String> list = Arrays.asList("c", "e", "a", "d", "b");
        // Stream<T> sorted(Comparator<? super T> comparator);
        // int compare(T o1, T o2);
        list.stream().sorted(Comparator.naturalOrder()).forEach(item -> System.out.print(item + "\t"));
        System.out.println("\n倒序：");
        list.stream().sorted(Comparator.reverseOrder()).forEach(item -> System.out.print(item + "\t"));
    }

    public void stu6() {
        // 知之为知之,不知为不知
        Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
        // know is noknow
        stream.distinct().forEach(item -> System.out.print(item + "\t"));
    }

    @Test
    public void stu7() {
        Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
        long count = stream.count();
        System.out.println(count);
    }

    @Test
    public void min() {
        List<Integer> list = Arrays.asList(31, 22, 133, 465, 125);
        // Optional<T> min(Comparator<? super T> comparator);
        Optional<Integer> optional = list.stream().min(Comparator.naturalOrder());
        Optional<Integer> optional2 = list.stream().min(Comparator.reverseOrder());
        Integer value = optional.get();
        System.out.println(value + "\t" + optional2.get());
    }

    @Test
    public void skip() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream<T> skip(long n)
        list.stream().skip(2).forEach(System.out::println);  // c、d、e
    }

    @Test
    public void limit() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        list.stream().skip(2).limit(2).forEach(System.out::println);    // c、d
    }

    @Test
    public void collect() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        // Stream -> Collection
        List<String> collect = list.stream().collect(Collectors.toList());

        // Stream -> Object[]
        Object[] objects = list.stream().toArray();
    }

    @Test
    public void concat() {
        List<String> list = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("c", "d");
        Stream<String> concatStream = Stream.concat(list.stream(), list2.stream());
        concatStream.forEach(System.out::println);
    }

    @Test
    public void match() {
        // 你给我站住
        List<String> list = Arrays.asList("you", "give", "me", "stop");
        // boolean anyMatch(Predicate<? super T> predicate);
        // parallelStream可以并行计算，速度比stream更快
        boolean result = list.parallelStream().anyMatch(item -> item.equals("me"));
        System.out.println(result);
    }

    @Test
    public void reduce() {
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        Optional<String> optional = stream.reduce((before, after) -> before + "," + after);
        optional.ifPresent(System.out::println);    // you,give,me,stop
    }

    @Test
    public void reduce2() {
        List<BigDecimal> list = Arrays.asList(
                new BigDecimal("11.11"),
                new BigDecimal("22.22"),
                new BigDecimal("33.33")
        );
        // 66.66
        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }

    @Test
    public void findFirst() {
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        String value = stream.findFirst().get();
        System.out.println(value);
    }

    @Test
    public void findAny() {
        Stream<String> stream = Stream.of("you", "give", "me", "stop");
        String value2 = stream.findAny().get();
        System.out.println(value2);
    }

    public static void main(String[] args) {
        StreamTest streamStu = new StreamTest();
        streamStu.stu1();
        System.out.println();
        streamStu.stu2();
        System.out.println();
        streamStu.stu3();
        System.out.println();
        streamStu.stu4();
        streamStu.stu5();
        System.out.println();
        streamStu.stu6();
    }
}
