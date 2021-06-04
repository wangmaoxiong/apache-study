package com.wmx.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/6/4 11:06
 */
public class FastjsonTest {

    /**
     * 将Java Bean List转Json字符串
     */
    @Test
    public void test1() {

        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(1, "张三", LocalDateTime.now(), false, 5688.99F));
        persons.add(new Person(2, "李斯", LocalDateTime.now(), false, 6688.99F));
        persons.add(new Person(3, "王五", LocalDateTime.now(), true, 8688.99F));

        String object = JSON.toJSONString(persons);
        System.out.println(object);
        /**输出如下：
         * [
         * {"birthday":"2021-06-04T11:09:11.882","id":1,"marry":false,"name":"张三","price":5688.99},
         * {"birthday":"2021-06-04T11:09:11.883","id":2,"marry":false,"name":"李斯","price":6688.99},
         * {"birthday":"2021-06-04T11:09:11.883","id":3,"marry":true,"name":"王五","price":8688.99}]
         */
    }

    /**
     * 将Json 数组 转 Java List
     */
    @Test
    public void test2() {
        String jsonArrPOJOStr = "[{\"birthday\":\"2021-06-04T11:09:11.882\",\"id\":1,\"marry\":false,\"name\":\"张三\",\"price\":5688.99},{\"birthday\":\"2021-06-04T11:09:11.883\",\"id\":2,\"marry\":false,\"name\":\"李斯\",\"price\":6688.99},{\"birthday\":\"2021-06-04T11:09:11.883\",\"id\":3,\"marry\":true,\"name\":\"王五\",\"price\":8688.99}]";
        List<Person> personList = JSONArray.parseArray(jsonArrPOJOStr, Person.class);

        System.out.println(personList);
        /**
         * 输出：
         * [Person{id=1, name='张三', birthday=2021-06-04T11:09:11.882, marry=false, price=5688.99},
         * Person{id=2, name='李斯', birthday=2021-06-04T11:09:11.883, marry=false, price=6688.99},
         * Person{id=3, name='王五', birthday=2021-06-04T11:09:11.883, marry=true, price=8688.99}]
         */
    }

    @Test
    public void test3() {
        String jsonArrPOJOStr = "[{\"birthday\":\"2022-06-04T11:09:11.882\",\"id\":1,\"marry\":false,\"name\":\"张三\",\"price\":5688.99},{\"birthday\":\"2021-06-04T11:09:11.883\",\"id\":2,\"marry\":false,\"name\":\"李斯\",\"price\":6688.99},{\"birthday\":\"2021-06-04T11:09:11.883\",\"id\":3,\"marry\":true,\"name\":\"王五\",\"price\":8688.99}]";
        List<Map> personList = JSONArray.parseArray(jsonArrPOJOStr, Map.class);

        System.out.println(personList);
        /**
         * 输出：
         * [{birthday=2022-06-04T11:09:11.882, price=5688.99, marry=false, name=张三, id=1},
         * {birthday=2021-06-04T11:09:11.883, price=6688.99, marry=false, name=李斯, id=2},
         * {birthday=2021-06-04T11:09:11.883, price=8688.99, marry=true, name=王五, id=3}]
         */
    }
}
