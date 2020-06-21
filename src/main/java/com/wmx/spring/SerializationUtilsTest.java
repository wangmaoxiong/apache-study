package com.wmx.spring;

import com.wmx.apachestudy.pojo.Person;
import org.springframework.util.SerializationUtils;

import java.util.Date;

/**
 * Spring-core 核心工具类 之 序列化工具类
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 13:56
 */
public class SerializationUtilsTest {

    public static void main(String[] args) {
        //被序列化的实体必须 implements Serializable 接口
        Person person = new Person(1001, "华安", new Date(), 8998.87f);
        byte[] serialize = SerializationUtils.serialize(person);
        Object deserialize = SerializationUtils.deserialize(serialize);
        System.out.println(deserialize);
    }
}
