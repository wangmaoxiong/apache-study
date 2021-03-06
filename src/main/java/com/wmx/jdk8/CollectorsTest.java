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
    public void toSet1() {
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
    public void groupingBy1() {
        Map<Integer, List<Integer>> integerListMap = Stream.of(1, 3, 23, 45, 32, 12, 44, 45, 32, 3, 3).collect(Collectors.groupingBy(item -> item));
        //{32=[32, 32], 1=[1], 3=[3, 3, 3], 23=[23], 44=[44], 12=[12], 45=[45, 45]}
        System.out.println(integerListMap);
    }

    /**
     * Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
     * 1、对元流中的元素进行分组
     */
    @Test
    public void groupingBy2() {
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
     * Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
     * 1、对元流中的元素进行分组
     */
    @Test
    public void groupingBy3() {
        List<Map<String, Object>> dataList = this.getDataList();

        Map<String, List<Map<String, Object>>> collect = dataList.stream().collect(Collectors.groupingBy(map -> {
            Optional<Object> c22 = Optional.ofNullable(map.get("age"));
            if (Integer.parseInt(c22.orElse("0") + "") < 30) {
                return "少年";
            } else if (Integer.parseInt(c22.orElse("0") + "") < 60) {
                return "中年";
            } else {
                return "老年";
            }
        }));

        /**
         * {老年=[{c21=删除, address=武汉市, agency_code=304100, age=87}, {c21=既往, address=深圳市, agency_code=324100, age=90}],
         * 中年=[{c21=新增, address=深圳市, agency_code=201025, age=30}, {c21=既往, address=长沙市, agency_code=002015, age=44}]}
         */
        System.out.println(collect);
    }

    @Test
    public void partitioningBy() {
        //Map<Boolean,List<Integer>>
        Map<Boolean, List<Integer>> collect = Stream.of(0, 1, 0, 1).collect(Collectors.partitioningBy(integer -> integer == 0));
        System.out.println(collect);//{false=[1, 1], true=[0, 0]}

        //Map<Boolean,Set<Integer>>
        //自定义下游收集器
        Map<Boolean, Set<Integer>> collect1 = Stream.of(0, 1, 0, 1).collect(Collectors.partitioningBy(integer -> integer == 0, Collectors.toSet()));
        System.out.println(collect1);//{false=[1], true=[0]}

        // 提取出 删除 和 新增的 单位
        List<Map<String, Object>> dataList = this.getDataList();
        Map<Boolean, List<Map<String, Object>>> listMap = dataList.stream().filter(map -> !"既往".equals(map.get("c21")))
                .collect(Collectors.partitioningBy(map -> "删除".equals(map.get("c21"))));
        /**
         * {false=[{c21=新增, address=深圳市, agency_code=201025, age=30}],
         * true=[{c21=删除, address=武汉市, agency_code=304100, age=87}]}
         */
        System.out.println(listMap);
    }


    /**
     * Collector<CharSequence, ?, String> joining(CharSequence delimiter)：将元素按遇到顺序 用指定的分隔符 连接起来
     */
    @Test
    public void joining1() {
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
    public void joining2() {
        List<String> stringList = Arrays.asList("121H", "431D", "728G", null, "9309I");
        String collect = stringList.stream().collect(Collectors.joining("','", "'", "'"));
        //'121H','431D','728G','null','9309I'
        System.out.println(collect);
    }

    /**
     * <T> Collector<T, ?, Long> counting()：统计输入元素的数量。如果不存在元素，则结果为0。
     */
    @Test
    public void counting() {
        List<String> stringList = Arrays.asList("121H", "431D", "728G", null, "9309I");
        Long counting = stringList.stream().collect(Collectors.counting());
        System.out.println(counting);//5
    }

    /**
     * <T> Collector<T, ?, Optional<T>> maxBy(Comparator<? super T> comparator) ：通过比较器获取最大值
     * <T> Collector<T, ?, Optional<T>> minBy(Comparator<? super T> comparator) ：通过比较器获取最小值
     */
    @Test
    public void maxBy() {
        List<Integer> integerList1 = Arrays.asList(1, 0, -10, 9, 8, 100, 200, -80);
        //获取集合中的最大值，不存在时返回 0
        Optional<Integer> optionalInteger = integerList1.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        System.out.println(optionalInteger.orElse(0));//200

        //获取集合中的最小值，不存在时返回 0，Collectors.minBy、Collectors.maxBy 排序取值時，元素不能為 null，否则异常
        List<Integer> integerList2 = Arrays.asList(1, 0, -10, 9, null, 100, 200, -80);
        Integer integer = integerList2.stream().filter(item -> Optional.ofNullable(item).isPresent()).collect(Collectors.minBy(Comparator.naturalOrder())).orElse(0);
        System.out.println(integer);//-80
    }

    /**
     * <T> Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> mapper)	对整数元素求和，如果不存在值，则返回 0
     * <T> Collector<T, ?, Long> summingLong(ToLongFunction<? super T> mapper) 对长整数元素求和，如果不存在值，则返回 0
     * <T> Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> mapper) 对浮点型元素求和，如果不存在值，则返回 0
     */
    @Test
    public void summingInt() {
        Integer collect = Stream.of(1, 22, 14, 25, 34, 33, 55, 43).collect(Collectors.summingInt(integer -> Integer.valueOf(integer)));
        System.out.println(collect);//227

        Double collect2 = Stream.of(11.2, 34.3, 34.3, 55.0).collect(Collectors.summingDouble(Double::doubleValue));
        System.out.println(collect2);//134.8
    }

    /**
     * <T> Collector<T, ?, IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper)：对 int 整型求摘要统计信息，包含：
     * getCount()：元素个数
     * getSum()：求和
     * getMin()：最小值
     * getAverage()：平均值
     * getMax())：最大值
     * Collector<T, ?, LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> mapper)
     * Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> mapper)
     */
    @Test
    public void summarizingInt() {
        IntSummaryStatistics intSummaryStatistics = Stream.of(1, 22, 14, 25, 34, 33, 55, 43).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(intSummaryStatistics);//IntSummaryStatistics{count=8, sum=227, min=1, average=28.375000, max=55}
        System.out.println(intSummaryStatistics.getSum());//227

        DoubleSummaryStatistics summaryStatistics = Stream.of(11.2, 34.3, 34.3, 55.0).collect(Collectors.summarizingDouble(dou -> dou.doubleValue()));
        System.out.println(summaryStatistics);//DoubleSummaryStatistics{count=4, sum=134.800000, min=11.200000, average=33.700000, max=55.000000}
        System.out.println(summaryStatistics.getAverage());//33.7
    }

    /**
     * 求元素的平均值
     * Collector<T, ?, Double> averagingLong(ToLongFunction<? super T> mapper)
     * Collector<T, ?, Double> averagingDouble(ToDoubleFunction<? super T> mapper)
     * Collector<T, ?, Double> averagingInt(ToIntFunction<? super T> mapper)
     */
    @Test
    public void averagingLong() {
        Double collect1 = Stream.of(1, 22, 14, 25, 34, 33, 55, 43).collect(Collectors.averagingLong(l -> l.longValue()));
        System.out.println(collect1);//28.375

        Double collect2 = Stream.of(11.2, 34.3, 34.3, 55.0).collect(Collectors.averagingDouble(d -> d.doubleValue()));
        System.out.println(collect2);//33.7
    }

    /**
     * Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> op)，内部函数方法 R apply(T t, U u)
     * apply 方法中的参数 t 表示当前计算的值，u 表示下一个元素，返回的值 r 会作为参数 t 继续传入，非常适合累加、累乘等等操作
     * Collector<T, ?, T> reducing(T identity, BinaryOperator<T> op): identity 定义默认值，当没有元素时返回的值
     * Collector<T, ?, U> reducing(U identity,Function<? super T, ? extends U> mapper,BinaryOperator<U> op)
     * mapper：应用于每个输入值的映射函数
     */
    @Test
    public void reducing() {
        // 累加操作，
        Integer integer = Stream.of(1, 22, 14, 25, 34, 33, 55, 43).collect(Collectors.reducing((i, j) -> i + j)).orElse(0);
        System.out.println(integer);//227

        List<Map<String, Object>> dataList = this.getDataList();
        /**
         * [{c21=新增, address=深圳市, agency_code=201025, age=30},
         * {c21=既往, address=长沙市, agency_code=002015, age=44},
         * {c21=删除, address=武汉市, agency_code=304100, age=87},
         * {c21=既往, address=深圳市, agency_code=324100, age=90}]
         */
        System.out.println(dataList);

        // 求年龄之和
        Integer collect = dataList.stream().map(item -> (Integer) item.get("age")).collect(Collectors.reducing(0, (i, j) -> i + j));
        System.out.println(collect);//251

        // 求年龄之和
        Integer age = dataList.stream().collect(Collectors.reducing(0, item -> (Integer) ((Map) item).get("age"), (i, j) -> i + j));
        System.out.println(age);//251
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
        map1.put("age", 30);

        map2.put("c21", "既往");
        map2.put("agency_code", "002015");
        map2.put("address", "长沙市");
        map2.put("age", 44);

        map3.put("c21", "删除");
        map3.put("agency_code", "304100");
        map3.put("address", "武汉市");
        map3.put("age", 87);

        map4.put("c21", "既往");
        map4.put("agency_code", "324100");
        map4.put("address", "深圳市");
        map4.put("age", 90);

        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
        dataList.add(map4);

        return dataList;
    }

}
