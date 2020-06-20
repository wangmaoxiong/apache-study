package com.wmx.spring;

import com.wmx.apachestudy.pojo.Person;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SimpleIdGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Spring-core 核心工具类 之 对象工具类，类似 {@link org.apache.commons.lang3.ObjectUtils}
 * 1、除了提供功能封装之外，还有一个好处就是老板再也不用担心空指针异常.
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/20 8:50
 */
public class ObjectUtilsTest {

    /**
     * boolean containsElement(@Nullable Object[] array, Object element)
     * 1、检测数组中是否包含指定的元素，array 为 null 时恒为 false
     */
    @Test
    public void containsElement() {
        String[] ids = new String[]{"解决", "蓝屏", "混合", null};
        //false,true,true
        System.out.print(ObjectUtils.containsElement(ids, "机票") + ",");
        System.out.print(ObjectUtils.containsElement(ids, "混合") + ",");
        System.out.print(ObjectUtils.containsElement(ids, null));
    }

    /**
     * String getDisplayString(@Nullable Object obj)
     * 1、将对象转为可视化的字符串，如果 obj 为 null，则恒返回空字符串""
     * 2、底层也是调用 {@link ObjectUtils#nullSafeToString(java.lang.Object)} 方法
     */
    @Test
    public void getDisplayString() {
        String code = "解决";
        String message = null;
        float[] floats = new float[]{23.4f, 45.5f, 56.5f};
        List<Object> objects = Arrays.asList("1f", "3k");
        Map<String, String> map = new HashMap<>(8);
        map.put("id", "9527");

        //解决,,{23.4, 45.5, 56.5},[1f, 3k],{id=9527}
        System.out.print(ObjectUtils.getDisplayString(code) + ",");
        System.out.print(ObjectUtils.getDisplayString(message) + ",");
        System.out.print(ObjectUtils.getDisplayString(floats) + ",");
        System.out.print(ObjectUtils.getDisplayString(objects) + ",");
        System.out.print(ObjectUtils.getDisplayString(map));
    }

    /**
     * String getIdentityHexString(Object obj)
     * 1、获取对象唯一的十六进制字符串，实质是将对象的哈希code转成了十六进制字符串
     */
    @Test
    public void getIdentityHexString() {
        String code = "解决它";
        String message = null;
        float[] floats = new float[]{33.4f, 45.5f, 56.5f};
        List<Object> objects = Arrays.asList("1f", "3k");
        Map<String, String> map = new HashMap<>(8);
        map.put("id", "9527");

        //2d363fb3,0,7d6f77cc,5aaa6d82,73a28541
        System.out.print(ObjectUtils.getIdentityHexString(code) + ",");
        System.out.print(ObjectUtils.getIdentityHexString(message) + ",");
        System.out.print(ObjectUtils.getIdentityHexString(floats) + ",");
        System.out.print(ObjectUtils.getIdentityHexString(objects) + ",");
        System.out.print(ObjectUtils.getIdentityHexString(map));
    }

    /**
     * String identityToString(@Nullable Object obj)
     * 1、获取对象的整体标识，obj.getClass().getName() + "@" + getIdentityHexString(obj);
     * 2、如果 obj 为 null，则恒返回空字符串 ""
     */
    @Test
    public void identityToString() {
        String code = "解决它";
        String message = null;
        float[] floats = new float[]{33.4f, 45.5f, 56.5f};
        List<Object> objects = Arrays.asList("1f", "3k", "8p");
        Map<String, String> map = new HashMap<>(4);
        map.put("id", "9528");

        //java.lang.String@2d363fb3,  ,   [F@7d6f77cc,    java.util.Arrays$ArrayList@5aaa6d82,   java.util.HashMap@73a28541
        System.out.print(ObjectUtils.identityToString(code) + ",  ");
        System.out.print(ObjectUtils.identityToString(message) + ",   ");
        System.out.print(ObjectUtils.identityToString(floats) + ",    ");
        System.out.print(ObjectUtils.identityToString(objects) + ",   ");
        System.out.print(ObjectUtils.identityToString(map));
    }

    /**
     * boolean isArray(@Nullable Object obj)
     * 1、判断对象是否为数组，为 null 时恒返回 false
     */
    @Test
    public void isArray() {
        float[] floats = new float[]{33.4f, 45.5f, 56.5f};
        float[] floats2 = null;
        Map<String, String> map = new HashMap<>(4);

        //true, false, false
        System.out.print(ObjectUtils.isArray(floats) + ", ");
        System.out.print(ObjectUtils.isArray(floats2) + ", ");
        System.out.print(ObjectUtils.isArray(map));
    }

    /**
     * boolean isCheckedException(Throwable ex)
     * 1、判断异常是否为受检查异常类型，源码为：!(ex instanceof RuntimeException || ex instanceof Error)
     * 2、即不是运行时异常，也不是 Error 时，则判定 ex 为受检查异常
     * boolean isCompatibleWithThrowsClause(Throwable ex, @Nullable Class<?>... declaredExceptions)
     * 1、判断受检查异常（ex）是否为指定的异常类型
     * 2、如果 ex 为运行时异常（RuntimeException）或者为 Error，则恒返回 true
     */
    @Test
    public void isCheckedException() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            //ArithmeticException extends RuntimeException 属于运行时异常，所以输出 false
            System.out.println("是否为受检查异常：" + ObjectUtils.isCheckedException(e));
            //isCompatibleWithThrowsClause 对运行时异常或者 Error 恒返回 true
            System.out.println("是否为运行时异常：" + ObjectUtils.isCompatibleWithThrowsClause(e));
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("mmmm"));
        } catch (FileNotFoundException e) {
            //FileNotFoundException extends IOException 属于受检查异常，即必须捕获处理的异常.
            System.out.println("是否为受检查异常：" + ObjectUtils.isCheckedException(e));
            System.out.println("是否为 IO 异常：" + ObjectUtils.isCompatibleWithThrowsClause(e, IOException.class));
            e.printStackTrace();
        }
    }

    /**
     * boolean isEmpty(@Nullable Object obj)
     * 1、检测对象是否为空，如果为 null 或者为空，则返回 true，字符串的空格不算空，返回 false.
     * 2、支持以下类型：{@link Optional}、Array、{@link CharSequence}、{@link Collection}、{@link Map}
     */
    @Test
    public void isEmpty() {
        Object obj = null;
        String code = " ";
        List<Object> asList = Arrays.asList();
        //true,false,true
        System.out.println(ObjectUtils.isEmpty(obj) + "," + ObjectUtils.isEmpty(code) + "," + ObjectUtils.isEmpty(asList));
    }

    /**
     * boolean isEmpty(@Nullable Object[] array)
     * 1、检查数组是否为空，源码：return (array == null || array.length == 0)
     */
    @Test
    public void isEmpty2() {
        Object[] objects = new Object[]{};
        String[] strings = new String[]{"1x"};
        System.out.println(ObjectUtils.isEmpty(objects) + "," + ObjectUtils.isEmpty(strings));
    }

    /**
     * String nullSafeClassName(@Nullable Object obj)
     * 1、获取对象的 className 值，当 obj 为 null 时返回字符串 "null"
     */
    @Test
    public void nullSafeClassName() {
        Float[] floats = new Float[]{33.4f, 45.5f, 56.5f};
        float[] floats2 = null;
        Map<String, String> map = new HashMap<>(4);

        //[Ljava.lang.Float;,  null,  java.util.HashMap,
        System.out.print(ObjectUtils.nullSafeClassName(floats) + ",  ");
        System.out.print(ObjectUtils.nullSafeClassName(floats2) + ",  ");
        System.out.print(ObjectUtils.nullSafeClassName(map) + ",  ");
    }

    /**
     * boolean nullSafeEquals(@Nullable Object o1, @Nullable Object o2)
     * 1、检查两个对象是否相等，当其中任意一个为 null，另一个不为 null 时，恒返回 false.
     * 2、先根据 "==" 判断，然后判断是否其中一个为 null，接着使用 o1.equals(o2) 比较，
     * 然后如果两个对象都是数组，则逐个比较其中的元素是否相等，前面4步都判断不出来，则返回false.
     */
    @Test
    public void nullSafeEquals() {
        //true
        System.out.println(ObjectUtils.nullSafeEquals(null, null));

        String s1 = "战国";
        String s2 = new String("战国");
        //true, false
        System.out.print(ObjectUtils.nullSafeEquals(s1, s2) + ", ");
        System.out.println(ObjectUtils.nullSafeEquals(s1, null));

        Float[] floats1 = new Float[]{23.4f, 45.4f, 566.55f};
        Float[] floats2 = new Float[]{23.4f, 45.4f, 566.55f};
        //true
        System.out.println(ObjectUtils.nullSafeEquals(floats1, floats2));

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("id", "9527");
        stringMap.put("name", "展示");

        Map<String, String> stringMap2 = new HashMap<>();
        stringMap2.put("id", "9527");
        stringMap2.put("name", "展示");
        //true
        System.out.println(ObjectUtils.nullSafeEquals(stringMap, stringMap2));

        Date date = new Date();
        Person person1 = new Person(1, "展示", date, 7888.89f);
        Person person2 = new Person(1, "展示", date, 7888.89f);
        //false，对应 POJO 对象默认使用的 Object 的 equals 方法，可以自己重写
        System.out.println(ObjectUtils.nullSafeEquals(person1, person2));
    }

    /**
     * int nullSafeHashCode(@Nullable Object obj)
     * 1、获取对象的 哈希 code 值，如果 obj 为null,则返回 0
     */
    @Test
    public void nullSafeHashCode() {
        String code = "解决2";
        String message = null;
        float[] floats = new float[]{23.4f, 45.5f, 56.53f};
        List<Object> objects = Arrays.asList("1f", "3k");
        Map<String, String> map = new HashMap<>(8);
        map.put("id", "9527");

        //34570754,  0,  111837124,  52900,  1751930
        System.out.print(ObjectUtils.nullSafeHashCode(code) + ",  ");
        System.out.print(ObjectUtils.nullSafeHashCode(message) + ",  ");
        System.out.print(ObjectUtils.nullSafeHashCode(floats) + ",  ");
        System.out.print(ObjectUtils.nullSafeHashCode(objects) + ",  ");
        System.out.print(ObjectUtils.nullSafeHashCode(map));
    }

    /**
     * String nullSafeToString(@Nullable Object obj)
     * 1、返回对象的字符串表现形式，如果 obj 为 null，则返回字符串 "null"
     */
    @Test
    public void nullSafeToString() {
        String code = "解决3";
        String message = null;
        float[] floats = new float[]{23.4f, 415.5f, 56.5f};
        List<Object> objects = Arrays.asList("1f", "3k");
        Map<String, String> map = new HashMap<>(8);
        map.put("id", "9527");

        //解决3,  null,  {23.4, 415.5, 56.5},  [1f, 3k],  {id=9527}
        System.out.print(ObjectUtils.nullSafeToString(code) + ",  ");
        System.out.print(ObjectUtils.nullSafeToString(message) + ",  ");
        System.out.print(ObjectUtils.nullSafeToString(floats) + ",  ");
        System.out.print(ObjectUtils.nullSafeToString(objects) + ",  ");
        System.out.print(ObjectUtils.nullSafeToString(map));

    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            SimpleIdGenerator simpleIdGenerator = new SimpleIdGenerator();
            UUID uuid = simpleIdGenerator.generateId();
            System.out.println(uuid);
        }
    }
}
