package com.wmx.jdk8;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1、jdk 1.8 的 java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
 * 2、Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，而中间操作继续返回 Stream，方便链式操作。
 * 3、Stream 的创建需要指定一个数据源，比如 java.util.Collection 的子类，List 或者 Set， Map不支持。
 * 4、Stream 的操作可以串行 stream() 执行或者并行 parallelStream() 执行。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/10 20:00
 */
@SuppressWarnings("all")
public class StreamTest {

    /**
     * forEach(Consumer<? super T> action) 对该流中的每个元素执行最终操作
     * 1、实现 {@link Consumer} 接口中 void accept(T t) 方法，t 表示流中当前遍历到的元素
     * 2、Stream<E> stream()：将集合转为流
     */
    @Test
    public void forEach1() {
        List<String> list = Arrays.asList("长沙", "深圳", "武汉", "伊犁", "洛阳", "开封");
        // 方式一：增强 for 循环
        for (String item : list) {
            System.out.print(item + "\t");
        }
        System.out.println();
        // 方式二：JDK 1.8 java.util.stream.Stream.forEach(Consumer<? super T> action)
        //长沙	深圳	武汉	伊犁	洛阳	开封
        list.stream().forEach(item -> System.out.print(item + "\t"));
    }

    /**
     * 遍历元素的时候，修改元素的值。比如遍历修改 List 中的对象
     */
    @Test
    public void forEach2() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("c20", "新增");
        map2.put("c20", "既往");
        dataList.add(map1);
        dataList.add(map2);

        dataList.stream().forEach(item -> item.put("c20", "删除"));
        //[{c20=删除}, {c20=删除}]
        System.out.println(dataList);
    }

    /**
     * Stream<T> filter(Predicate<? super T> predicate) 元素过滤，返回符合条件的元素，这是一个中间操作，返回结果流
     * 1、需要实现 {@link Predicate} 接口中的 boolean test(T t) 方法,t 为流中的元素，返回 false 的元素将会丢弃.
     */
    @Test
    public void filter1() {
        List<Integer> integerList = Arrays.asList(8, 12, 28, 19, 22, 39, 33, 44, 54, 33, 23);
        integerList.stream().filter(integer -> integer >= 18 && integer < 45).forEach(integer -> {
            //28	19	22	39	33	44	33	23
            System.out.print(integer + "\t");
        });
    }

    @Test
    public void filter2() {
        Object[] res = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).filter(i -> i % 2 == 0).filter(i -> i > 3).toArray();
        System.out.println(Arrays.toString(res));//[4, 6, 8]
    }


    /**
     * Stream<R> map(Function<? super T, ? extends R> mapper)
     * 1、元素映射，实质就是用于处理元素，然后返回处理结果，这是一个中间操作，返回一个结果流
     */
    @Test
    public void map1() {
        List<String> list = Arrays.asList("how", "are", "you", ",", "I", "am", "fine", "!");
        //对每个单词的首字母转大写
        Stream<String> stringStream = list.stream().map(item -> {
            if (item.length() > 1) {
                return item.substring(0, 1).toUpperCase() + "" + item.substring(1);
            } else {
                return item;
            }
        });
        //[how, are, you, ,, I, am, fine, !]
        System.out.println(list);
        //How Are You , I Am Fine !
        stringStream.forEach(item -> System.out.print(item + " "));
    }

    @Test
    public void map2() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(2);
        Map<String, Object> map2 = new HashMap<>(2);
        map1.put("c21", "新增");
        map2.put("c21", "既往");
        dataList.add(map1);
        dataList.add(map2);

        Stream<Map<String, Object>> mapStream = dataList.stream().map(item -> {
            item.put("c21", "离休");
            return item;
        });

        //[{c21=新增}, {c21=既往}]
        System.out.println(dataList);

        List<Map<String, Object>> mapList = mapStream.collect(Collectors.toList());
        //[{c21=离休}, {c21=离休}]
        System.out.println(mapList);

    }

    /**
     * 提取 List Map 中指定字段的值
     */
    @Test
    @SuppressWarnings("all")
    public void map3() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(2);
        Map<String, Object> map2 = new HashMap<>(2);

        map1.put("c21", "新增");
        map2.put("c21", "删除");
        dataList.add(map1);
        dataList.add(map2);

        List<Object> keySumList = dataList.stream().map(e -> e.get("c21")).collect(Collectors.toList());
        System.out.println(keySumList);

    }


    /**
     * Stream<T> of(T... values) ：将集合转为有顺序的流，可将多个集合合成一个流
     * collect(Collector<? super T, A, R> collector)：将流转为集合
     */
    @Test
    public void of1() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(41, 52, 63);
        List<List<Integer>> collect = Stream.of(a, b).collect(Collectors.toList());
        //[[1, 2, 3], [41, 52, 63]]
        System.out.println(collect);
    }

    /**
     * Stream<T> of(T... values) ：参数可以是集合，数组，基本类型，也可以是任何 POJO 对象
     */
    @Test
    public void of2() {
        Stream<String> stream = Stream.of("覆巢之下", "安有完卵", "天下攘攘", "皆为利往");

        //覆巢之下,安有完卵,天下攘攘,皆为利往,
        stream.forEach(item -> System.out.print(item + ","));
    }

    /**
     * Stream<T> distinct() ：对元素进行去重
     */
    @Test
    public void distinct() {
        Stream<String> stream = Stream.of("秦汗", "武汉", "汉武", "武汉", "大楚");
        //秦汗	武汉	汉武	大楚
        stream.distinct().forEach(item -> System.out.print(item + "\t"));
    }

    /**
     * Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) 将多个集合中的元素合并成一个集合
     */
    @Test
    public void flatMap() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(41, 51, 61);
        Stream<Integer> stream = Stream.of(a, b).flatMap(list -> list.stream());
        //2 4 6 82 102 122
        stream.forEach(item -> {
            item = 2 * item;
            System.out.print(item + " ");
        });
    }

    /**
     * Builder<T> builder(): 创建构建器，用于构建 java.util.stream.Stream
     * Builder<T> add(T t): 向正在生成的流中添加元素。
     * Stream<T> build(): 生成流
     * collect(Collector<? super T, A, R> collector): 将流转为集合
     */
    @Test
    public void builder() {
        Stream<Object> stream = Stream.builder().add("大秦").add("大商").add("大魏").build();
        List<Object> objectList = stream.collect(Collectors.toList());
        //[大秦, 大商, 大魏]
        System.out.println(objectList);
    }


    /**
     * java.util.stream.Collectors :一个有实现{@link Collector} 有用的工具类
     * toList(): 流转 List，还有 toSet()、toMap 等
     */
    @Test
    public void collectors() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Set<String> collect = list.stream().collect(Collectors.toSet());
        //a b c d e
        collect.stream().forEach(item -> System.out.print(item + " "));
    }

    /**
     * Stream<T> sorted(Comparator<? super T> comparator) :排序操作
     * {@link Comparator} 用于比较的函数，naturalOrder 顺序排序，reverseOrder 倒序排序
     */
    @Test
    public void sorted() {
        List<String> list = Arrays.asList("c", "e", "a", "d", "b");

        List<String> collect1 = list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        //[a, b, c, d, e]
        System.out.println("顺序：" + collect1);

        List<String> collect2 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //倒序：[e, d, c, b, a]
        System.out.println("倒序：" + collect2);
        //源列表：[c, e, a, d, b]
        System.out.println("源列表：" + list);

    }

    /**
     * long count() :统计流中元素个数
     */
    @Test
    public void count() {
        Stream<String> stream = Stream.of("c", "e", "a", "d", "b");
        long count = stream.count();
        System.out.println(count);
    }

    /**
     * Optional<T> min(Comparator<? super T> comparator) ：获取元素中的最小值
     * Optional<T> max(Comparator<? super T> comparator) ：获取元素中的最大值
     */
    @Test
    public void minAndMax() {
        List<Integer> list = Arrays.asList(31, 22, 133, 465, 125);
        // Optional<T> min(Comparator<? super T> comparator);
        Optional<Integer> optional = list.stream().min(Comparator.naturalOrder());
        Optional<Integer> optional2 = list.stream().max(Comparator.naturalOrder());
        Integer value1 = optional.get();
        Integer value2 = optional2.get();
        //22	465
        System.out.println(value1 + "\t" + value2);
    }

    /**
     * Stream<T> skip(long n) : 表示跳过 n 个元素，单 n 超过实际个数时，返回空的流
     * Stream<T> limit(long maxSize): 表示截取 maxSize 个元素，当 maxSize 超过实际个数时，则最多截取实际长度
     */
    @Test
    public void skipAndLimit() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        //c d e
        list.stream().skip(2).limit(3).forEach(item -> System.out.print(item + " "));
    }

    /**
     * Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b): 拼接两个流为一个流
     */
    @Test
    public void concat() {
        List<String> list = Arrays.asList("a", "b");
        List<Integer> list2 = Arrays.asList(110, 120);
        Stream<Object> concatStream = Stream.concat(list.stream(), list2.stream());
        //a b 110 120
        concatStream.forEach(item -> System.out.print(item + " "));
    }

    /**
     * Stream<E> parallelStream() 可以并行计算，速度比 stream 更快
     * boolean anyMatch(Predicate<? super T> predicate): 只要流中的任意一个元素满足条件，则返回 true
     */
    @Test
    public void anyMatch() {
        List<String> list = Arrays.asList("长沙", "长安", "常州", "昌平");
        boolean result1 = list.parallelStream().anyMatch(item -> item.equals("长安"));
        boolean result2 = list.parallelStream().anyMatch(item -> item.equals("西安") || item.contains("安"));
        //true	true
        System.out.println(result1 + "\t" + result2);
    }

    /**
     * boolean allMatch(Predicate<? super T> predicate): 流中的元素全部满足条件时，返回 true
     */
    @Test
    public void allMatch() {
        List<Integer> list = Arrays.asList(22, 34, 55, 43, 28);
        boolean allMatch1 = list.stream().allMatch(item -> item >= 18);
        boolean allMatch2 = list.parallelStream().allMatch(item -> item >= 28);
        //true,false
        System.out.println(allMatch1 + "," + allMatch2);
    }

    /**
     * Stream API 提供数字流 包括 IntStream、DoubleStream、LongStream
     * 1、int sum() : 对流中的元素进行求和，底层也是使用 reduce(0, Integer::sum) 方法
     */
    @Test
    public void sum() {
        IntStream intNumbers = IntStream.of(1, 4, 2, 3, 5, 5);
        //20
        int sum = intNumbers.sum();
        System.out.println(sum);
    }

    /**
     * DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)：将对象流转换为 DoubleStream
     * 1、{@link ToDoubleFunction} 接口中有一个 double applyAsDouble(T value)，函数式接口接收一个参数，并将处理结果以  double 类型返回
     */
    @Test
    public void mapToDouble() {
        //对 double 类型列表中的所有元素进行求和
        List<Double> doubleList = Arrays.asList(223.582, 52.426, 113.532);
        double result = doubleList.stream().mapToDouble(aDouble -> aDouble.doubleValue()).sum();
        //389.53999999999996
        System.out.println(result);

        //格式化数字，保留小数点后四位
        NumberFormat numberFormat = NumberFormat.getInstance();
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#,###.0000");
        String format = decimalFormat.format(result);
        //389.5400
        System.out.println(format);
    }

    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator)
     * 1、函数式接口 BinaryOperator，继承于 BiFunction，其中有一个 R apply(T t, U u) 方法
     * 2、apply 方法中的参数 t 表示当前计算的值，u 表示下一个元素。
     * 3、返回的值 r 会作为参数 t 继续传入，非常适合累加、累乘等等操作
     * 4、boolean isPresent()：如果存在值，则返回 true，否则返回 false.
     * 5、如果 {@code Optional} 中有值，则返回该值，否则抛出异常。
     */
    @Test
    public void reduce1() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10);
        Optional<Integer> reduce = integerList.stream().reduce((total, item) -> {
            total += item;
            return total;
        });
        boolean present = reduce.isPresent();
        if (present) {
            //1-10累加结果=51
            System.out.println("1-10累加结果=" + reduce.get());
        }
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator)
     * identity – 在 reduce(BinaryOperator<T> accumulator) 的基础上提供一个初始值
     * accumulator – 与 reduce(BinaryOperator<T> accumulator) 一致
     */
    @Test
    public void reduce2() {
        Stream<Integer> intNumbers = Stream.of(5, 1, 100);
        int result = intNumbers.reduce(10, (a, b) -> Integer.sum(a, b));
        //116
        System.out.println(result);
    }

    @Test
    public void reduce3() {
        Stream<BigDecimal> bigDecimalNumber = Stream.of(BigDecimal.valueOf(100), BigDecimal.valueOf(210));
        BigDecimal result = bigDecimalNumber.reduce(BigDecimal.ZERO, (total, item) -> total.add(item));
        //BigDecimal 类型流式求和：310
        System.out.println("BigDecimal 类型流式求和：" + result);
    }
}
