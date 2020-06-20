package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Spring-core 核心包工具类 之 断言
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/18 15:29
 */
public class AssertTest {

    /**
     * doesNotContain(@Nullable String textToSearch, String substring, String message)
     * 1、断言给定的文本（textToSearch）不包含给定的子字符串（substring）
     * 2、如果 textToSearch 包含子字符串（substring），则抛异常 IllegalArgumentException
     * 3、message：断言失败时要使用的异常消息
     */
    @Test
    public void doesNotContain() {
        String message = "修长城的民族！";
        String target1 = "长沙";
        String target2 = "长城";
        Assert.doesNotContain(message, target1, "结果中未发现目标值[" + target1 + "]");
        Assert.doesNotContain(message, target2, "结果中未发现目标值[" + target2 + "]");
    }

    /**
     * hasLength(@Nullable String text, String message)
     * 1、断言给定的字符串不是空的，空格不算作空
     * 2、如果文本为空，或者为 null，则抛出 IllegalArgumentException
     */
    @Test
    public void hasLength() {
        String message1 = "修长城的民族！";
        String message2 = "";
        Assert.hasLength(message1, "目标值为空[" + message1 + "]");
        Assert.hasLength(message2, "目标值为空[" + message2 + "]");
    }

    /**
     * hasText(@Nullable String text, String message)
     * 1、断言给定的字符串包含有效的文本内容；text 不能是 null，且必须至少包含一个非空白字符。
     * 2、如果 text 不包含有效的文本内容，则抛出 IllegalArgumentException
     */
    @Test
    public void hasText() {
        String message1 = "修长城的民族！";
        String message2 = " ";
        Assert.hasText(message1, "目标值为空[" + message1 + "]");
        Assert.hasText(message2, "目标值为空[" + message2 + "]");
    }

    /**
     * isAssignable(Class<?> superType, @Nullable Class<?> subType, String message)
     * 1、断言 subType 是否为 superType 的子类型，如果不是则抛出 IllegalArgumentException 异常
     * 2、注意必须是子类型，同一类型也会报错
     */
    @Test
    public void isAssignable() {
        List<String> idList = new ArrayList(8);
        Map<String, String> userMap = new HashMap(8);

        Assert.isAssignable(Collection.class, idList.getClass(), "参数非 Collection 类型");
        Assert.isAssignable(Collection.class, userMap.getClass(), "参数非 Collection 类型");
    }

    /**
     * isInstanceOf(Class<?> type, @Nullable Object obj, String message)
     * 1、断言所提供的对象（obj）是所提供类（type）的实例。
     * 2、如果对象不是类型的实例，则抛出 IllegalArgumentException
     */
    @Test
    public void isInstanceOf() {
        List<String> idList = new ArrayList(8);
        Map<String, String> userMap = new HashMap(8);

        Assert.isInstanceOf(Collection.class, idList, "idList 不是 Collection 的实例");
        Assert.isInstanceOf(Collection.class, userMap, "userMap 不是 Collection 的实例");
    }

    /**
     * isNull(@Nullable Object object, String message)
     * 1、断言对象（object）是 null，如果不是 null，则抛出 IllegalArgumentException
     */
    @Test
    public void isNull() {
        Map<String, String> userMap = null;
        List<String> idList = new ArrayList(8);

        Assert.isNull(userMap, "userMap 为 null");
        Assert.isNull(idList, "idList 为 null");
    }

    /**
     * notNull(@Nullable Object object, String message)
     * 1、断言对象不是 null，如果是 null，则抛出 IllegalArgumentException
     */
    @Test
    public void notNull() {
        List<String> idList = new ArrayList(8);
        Map<String, String> userMap = null;

        Assert.notNull(userMap, "userMap 为 null");
        Assert.notNull(idList, "idList 为 null");
    }

    /**
     * isTrue(boolean expression, String message)
     * 1、断言布尔表达式，如果表达式计算结果为 false，则抛出{@link IllegalArgumentException}
     * 2、message：断言失败时要使用的异常消息
     */
    @Test
    public void isTrue() {
        String responseCode1 = "200";
        String responseCode2 = "404";
        Assert.isTrue("200".equalsIgnoreCase(responseCode1), "请求失败，状态码[" + responseCode1 + "]");
        Assert.isTrue("200".equalsIgnoreCase(responseCode2), "请求失败，状态码[" + responseCode2 + "]");
    }

    /**
     * state(boolean expression, String message)
     * 1、断言布尔表达式，如果表达式的计算结果为 false，则抛出 IllegalArgumentException
     * 2、和 {@link Assert#isTrue(boolean, java.lang.String)} 实现的效果一致.
     */
    @Test
    public void state() {
        String responseCode1 = "200";
        String responseCode2 = "404";
        Assert.state("200".equalsIgnoreCase(responseCode1), "请求失败，状态码[" + responseCode1 + "]");
        Assert.state("200".equalsIgnoreCase(responseCode2), "请求失败，状态码[" + responseCode2 + "]");
    }


    /**
     * noNullElements(@Nullable Object[] array, String message)
     * 1、断言数组不包含 null 元素
     * 2、array 可以为 null，可以为空，有元素时，只要有一个为 null，则抛出 IllegalArgumentException
     */

    @Test
    public void noNullElements() {
        String[] arr1 = null;
        String[] arr2 = new String[]{};
        String[] arr3 = new String[]{"中国", null, "魏国"};
        Assert.noNullElements(arr1, "arr1 数组中含有 null 元素");
        Assert.noNullElements(arr2, "arr2 数组中含有 null 元素");
        Assert.noNullElements(arr3, "arr3 数组中含有 null 元素");
    }

    /**
     * notEmpty(@Nullable Collection<?> collection, String message)
     * 1、断言集合包含元素，即不能为 null，也不能为空，必须至少包含一个元素，否则抛出 IllegalArgumentException
     * notEmpty(@Nullable Map<?, ?> map, String message)
     * 2、断言 Map 包含元素，即不能为 null，也不能为空，必须至少包含一个元素，否则抛出 IllegalArgumentException
     * notEmpty(@Nullable Object[] array, String message)
     * 3、断言 array 包含元素，即不能为 null，也不能为空，必须至少包含一个元素，否则抛出 IllegalArgumentException
     */
    @Test
    public void notEmpty() {
        List<Object> list = Arrays.asList(1, 2, 3);
        Map<String, String> map = null;
        Assert.notEmpty(list, "list 不能为空集合");
        Assert.notEmpty(map, "map 不能为空 Map");
    }
}
