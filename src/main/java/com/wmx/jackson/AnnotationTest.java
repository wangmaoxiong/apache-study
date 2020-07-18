package com.wmx.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link com.fasterxml.jackson.annotation} 注解测试
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/17 8:26
 */
@SuppressWarnings("all")
public class AnnotationTest {

    /**
     * 1、对象序列化为 json 字符串，日期默认会转为 long 长整型，可以使用 @JsonFormat 注解加到指定对象的属性上指定日期格式
     * 2、json 字符串反序列化为 POJO 时照样将长整型的日期转为 Date 类型
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        Book book = new Book(10001, "三国演义", 56.68F, new Date());
        ObjectMapper objectMapper = new ObjectMapper();

        //对象序列化为 json 字符串
        String json = objectMapper.writeValueAsString(book);
        System.out.println(json);//{"id":10001,"title":"三国演义","price":56.68,"publish":1594946350709}

        //json 字符串反序列化为 POJO
        Book book1 = objectMapper.readValue(json, Book.class);
        System.out.println(book1);//Book{id=10001, title='三国演义', price=56.68, publish=Fri Jul 17 08:39:10 CST 2020}
    }

    /**
     * Map 对象序列化为 json 字符串时，日期也会默认转为 long 长整型
     * json 字符串中日期为长整型，反序列化为 POJO 时，即使 POJO 上使用注解指定了日期格式，也不会影响反序列化
     */
    @Test
    public void test2() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //Map 对象序列化为 json 字符串
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", 5009);
            dataMap.put("publish", new Date());
            String valueAsString1 = objectMapper.writeValueAsString(dataMap);
            System.out.println(valueAsString1);//{"publish":1572513438085,"id":5009}

            //json 字符串反序列化为 POJO
            Book book2 = objectMapper.readValue(valueAsString1, Book.class);
            //Book{id=5009, title='null', price=null, publish=Fri Jul 17 08:46:24 CST 2020}
            System.out.println(book2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JsonNode 节点对象 put 数据时，有 8  种基本数据类型以及 String、BigDecimal、BigInteger，但是没有 Date 类型
     * 所以日期类型只能通过 Long 长整型设置，但是转 POJO 对象时仍然会自动转为 Date 类型
     */
    @Test
    public void test3() {
        try {
            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("uId", 3200);
            objectNode.put("uName", "李世民");
            objectNode.put("birthday", new Date().getTime());

            User user = new ObjectMapper().treeToValue(objectNode, User.class);
            //User{uId=3200, uName='李世民', birthday=Fri Jul 17 20:34:13 CST 2020, price=null}
            System.out.println(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1、下面的情况也需要注意，Map 对象中日期类型序列化后变为 Long 长整型
     * 2、JsonNode readTree(String content) 反序列化为 json 节点时，长整型的日期仍旧是长整型
     * 3、T readValue(String content, Class<T> valueType)：反序列化为 Map 对象时，长整型的日期仍旧是长整型
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 8890);
        dataMap.put("title", "三国志");
        dataMap.put("price", null);
        dataMap.put("publish", new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(dataMap);
        //默认情况下，日期 date 类型会被序列化为 Long 型
        System.out.println(valueAsString);//{"price":null,"publish":1572511299179,"id":8890,"title":"三国志"}

        //反序列为 json 对象，此时日期会被反序列为 Long 型。
        JsonNode jsonNode = objectMapper.readTree(valueAsString);
        System.out.println(jsonNode);//{"price":null,"publish":1572511299179,"id":8890,"title":"三国志"}

        //反序列为对象，显然 publish 也已经是 Long 类型了
        Map readValue = objectMapper.readValue(valueAsString, Map.class);
        System.out.println(readValue);//{price=null, publish=1572511506126, id=8890, title=三国志}
    }

    /**
     * 演示 JsonInclude(JsonInclude.Include.NON_NULL)：对值为 null 的属性不进行序列化
     */
    @Test
    public void test5() {
        try {
            Book book = new Book(10001, "三国演义", null, new Date());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(book);
            //{"id":10001,"title":"三国演义","publish":"2020-07-17 09:01:09"}
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
