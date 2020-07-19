package com.wmx.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Date;

/**
 * {@link GsonBuilder}
 * 1、对于默认配置的 {@link Gson}，直接使用 new Gson 更简单
 * 2、当需要设置默认值以外的配置选项时，使用 {@link GsonBuilder} 构建器，调用它的各种配置方法，最后调用 create 方法创建 Gson 对象
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/16 8:27
 */
public class GsonBuilderTest {

    /**
     * GsonBuilder serializeNulls(): 序列化空字段,默认情况下，Gson 序列化期间会忽略所有为 null 的字段
     * GsonBuilder setDateFormat(String pattern)：根据提供的模式序列化 Date 对象，可以多次调用，但只有最后一次调用将用于决定序列化格式。
     * GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention)：
     * 1、配置为在序列化和反序列化期间将特定命名策略应用于对象的字段。
     * 2、namingConvention：用于序列化和反序列化的 JSON 字段命名约定/策略。
     * <p>
     * 2.1、IDENTITY：使用对象默认的字段名称
     * 2.2、UPPER_CAMEL_CASE：使用驼峰命名
     * 2.3、UPPER_CAMEL_CASE_WITH_SPACES：使用此命名策略将确保 Java 字段名的第一个'字母'在序列化为JSON格式时大写，单词之间用空格隔开
     * 2.4、LOWER_CASE_WITH_UNDERSCORES：使用此命名策略将把 Java 字段名从大小写形式修改为全小写字段名，其中单词之间由下划线分隔
     * 2.5、LOWER_CASE_WITH_DASHES：使用此命名策略将把 Java 字段名从大小写形式修改为全小写字段名，其中单词之间用破折号（-）分隔
     * 2.6、LOWER_CASE_WITH_DOTS：使用此命名策略将 Java 字段名从大小写形式修改为全小写字段名，其中每个单词用点（.）分隔。
     * </p>
     * Gson create(): 基于当前配置创建{@link Gson}实例,可以多次调用.
     *
     */
    @Test
    public void test1() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        Person person = new Person();
        person.setId(100);
        person.setBirthday(new Date());

        String toJson = gson.toJson(person);
        //{"id":100,"name":null,"birthday":"2020-07-19 11:37:35","marry":null}
        System.out.println(toJson);

        Person person1 = gson.fromJson(toJson, Person.class);
        //Person{id=100, name='null', birthday=Sun Jul 19 11:37:35 CST 2020, marry=null}
        System.out.println(person1);
    }
}
