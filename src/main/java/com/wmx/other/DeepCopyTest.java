package com.wmx.other;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.*;

/**
 * Java 对象深度克隆
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/31 16:39
 */
public class DeepCopyTest {

    @Test
    public void deepCopyMapTest1() throws IOException, ClassNotFoundException {
        Map<String, Object> map1 = new HashMap<>(2);
        map1.put("id", 3000);
        map1.put("name", "华安");

        Map<String, Object> deepCopy1 = deepCopy(map1);
        Map<String, Object> deepCopy2 = cloneObject(map1);

        deepCopy1.put("age", 45);
        deepCopy2.put("marry", false);

        //{name=华安, id=3000}
        System.out.println(map1);
        //{name=华安, id=3000, age=45}
        System.out.println(deepCopy1);
        //{name=华安, marry=false, id=3000}
        System.out.println(deepCopy2);
    }

    @Test
    public void deepCopyMapTest2() throws IOException, ClassNotFoundException {
        Person person = new Person(11, "无忌", new Date(), 7878.89F);
        Map<String, Person> map1 = new HashMap<>(2);
        map1.put("11", person);

        Map<String, Person> deepCopy = deepCopy(map1);
        Map<String, Person> cloneObject = cloneObject(map1);
        deepCopy.get("11").setName("悟空");
        cloneObject.get("11").setId(20002);

        //{11=Person{id=11, name='无忌', birthday=Tue Aug 11 14:57:06 CST 2020, salary=7878.89}}
        System.out.println(map1);
        //{11=Person{id=11, name='悟空', birthday=Tue Aug 11 14:57:06 CST 2020, salary=7878.89}}
        System.out.println(deepCopy);
        //{11=Person{id=20002, name='无忌', birthday=Tue Aug 11 14:57:06 CST 2020, salary=7878.89}}
        System.out.println(cloneObject);

    }

    @Test
    public void deepCopyListTest1() throws IOException, ClassNotFoundException {
        List<String> list = new ArrayList<>(Arrays.asList("中", "会话", "品牌"));
        List<String> copyList = deepCopy(list);
        List<String> cloneObject = cloneObject(list);

        copyList.add("环境");
        cloneObject.add("回家");

        //[中, 会话, 品牌]
        System.out.println(list);
        //[中, 会话, 品牌, 环境]
        System.out.println(copyList);
        //[中, 会话, 品牌, 回家]
        System.out.println(cloneObject);
    }

    @Test
    public void deepCopyListTest2() throws IOException, ClassNotFoundException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(2);
        map1.put("id", 3000);
        map1.put("name", "华安");
        mapList.add(map1);

        List<Map<String, Object>> cloneList = deepCopy(mapList);
        List<Map<String, Object>> cloneObject = cloneObject(mapList);

        cloneList.get(0).put("id", "2000");
        cloneObject.get(0).put("name", "悟空");

        //[{name=华安, id=3000}]
        System.out.println(mapList);
        //[{name=华安, id=2000}]
        System.out.println(cloneList);
        //[{name=悟空, id=3000}]
        System.out.println(cloneObject);
    }

    @Test
    public void deepCopyListTest3() throws IOException, ClassNotFoundException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(11, "无忌", new Date(), 7878.89F));

        List<Person> copyList = deepCopy(personList);
        List<Person> cloneObject = cloneObject(personList);

        copyList.get(0).setName("后裔");
        cloneObject.get(0).setSalary(556576.67F);

        //[Person{id=11, name='无忌', birthday=Tue Aug 11 14:59:32 CST 2020, salary=7878.89}]
        System.out.println(personList);
        //[Person{id=11, name='后裔', birthday=Tue Aug 11 14:59:32 CST 2020, salary=7878.89}]
        System.out.println(copyList);
        //[Person{id=11, name='无忌', birthday=Tue Aug 11 14:59:32 CST 2020, salary=556576.7}]
        System.out.println(cloneObject);
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
     * 对象克隆。使用 java 序列化的方式将对象进行克隆。
     *
     * @param src ：被克隆的对象，如 java bean，Set、List、Map 等等
     * @param <E> ：支持任意类型，如果是 java Bean，则必须实现 {@link Serializable} 接口实现序列化，否则报错
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <E> E cloneObject(E src) throws IOException, ClassNotFoundException {
        Assert.isTrue(!ObjectUtils.isEmpty(src), "参数不能为空");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        E dest = (E) in.readObject();
        return dest;
    }

}
