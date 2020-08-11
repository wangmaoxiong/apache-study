package com.wmx.other;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.*;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/31 16:39
 */
public class DeepCopyTest {

    @Test
    public void deepCopyListTest1() throws IOException, ClassNotFoundException {
        List<String> list = new ArrayList<>(Arrays.asList("中", "会话", "品牌"));
        List<String> copyList = deepCopy(list);
        copyList.add("环境");

        //[中, 会话, 品牌]
        System.out.println(list);
        //[中, 会话, 品牌, 环境]
        System.out.println(copyList);
    }

    @Test
    public void deepCopyListTest2() throws IOException, ClassNotFoundException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(2);
        map1.put("id", 3000);
        map1.put("name", "华安");
        mapList.add(map1);

        List<Map<String, Object>> cloneList = deepCopy(mapList);
        cloneList.get(0).put("id", "2000");
        cloneList.get(0).put("name", "悟空");

        //[{name=华安, id=3000}]
        System.out.println(mapList);
        //[{name=悟空, id=2000}]
        System.out.println(cloneList);
    }

    @Test
    public void deepCopyListTest3() throws IOException, ClassNotFoundException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(11, "无忌", new Date(), 7878.89F));

        List<Person> copyList = deepCopy(personList);
        copyList.get(0).setName("后裔");

        System.out.println(personList);
        System.out.println(copyList);
    }

    @Test
    public void deepCopyMapTest1() throws IOException, ClassNotFoundException {
        Map<String, Object> map1 = new HashMap<>(2);
        map1.put("id", 3000);
        map1.put("name", "华安");

        Map<String, Object> deepCopy = deepCopy(map1);
        deepCopy.put("age", 45);

        System.out.println(map1);
        System.out.println(deepCopy);
    }

    @Test
    public void deepCopyMapTest2() throws IOException, ClassNotFoundException {
        Person person = new Person(11, "无忌", new Date(), 7878.89F);
        Map<String, Person> map1 = new HashMap<>(2);
        map1.put("11", person);

        Map<String, Person> deepCopy = deepCopy(map1);
        deepCopy.get("11").setName("悟空");

        System.out.println(map1);
        System.out.println(deepCopy);

    }

    /**
     * List 对象深度复制
     *
     * @param src ：被克隆的对象，如：List<String>、List<Map<String, Object>>、List<Person> 等等
     * @param <T> ：支持任意类型，如果是 java Bean，则必须实现 {@link Serializable} 接口实现序列化，否则报错
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        Assert.isTrue(!ObjectUtils.isEmpty(src), "参数不能为空！");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * Map 对象深度复制
     *
     * @param src 被克隆的对象，如：Map<String, Object>、Map<String, Person>等等
     * @param <K>
     * @param <V> :支持任意类型，如果是 java Bean，则必须实现 {@link Serializable} 接口实现序列化，否则报错
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <K, V> Map<K, V> deepCopy(Map<K, V> src) throws IOException, ClassNotFoundException {
        Assert.isTrue(!ObjectUtils.isEmpty(src), "参数不能为空");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Map<K, V> dest = (Map<K, V>) in.readObject();
        return dest;
    }

}
