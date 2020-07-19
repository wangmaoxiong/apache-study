package com.wmx.gson;

import com.google.gson.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * {@link JsonArray}
 * 1、表示 json 数组类型，是 {@link JsonElement} 的子类
 * 2、这是一个有序列表，和元素添加时的顺序一致.
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/14 16:16
 */
@SuppressWarnings("all")
public class JsonArrayTest {

    /**
     * 往数组中添加普通数据类型元素
     * add(Boolean bool)
     * add(Character character)
     * add(Number number)
     * add(String string)
     */
    @Test
    public void test1() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(false);
        jsonArray.add(3.14159F);
        jsonArray.add("华安");
        jsonArray.add('A');
        System.out.println(jsonArray);//[false,3.14159,"华安","A"]
    }

    /**
     * add(JsonElement element)：添加 json 元素
     * ddAll(JsonArray array)：将指定 json 数组中的所有元素添加到自己中
     */
    @Test
    public void test2() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 200);
        jsonObject.addProperty("msg", "成功");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);//[{"code":200,"msg":"成功"}]

        JsonArray jsonArray1 = new JsonArray();
        jsonArray1.addAll(jsonArray);
        jsonArray1.addAll(jsonArray);
        System.out.println(jsonArray1);//[{"code":200,"msg":"成功"},{"code":200,"msg":"成功"}]
    }

    /**
     * 创建 JSON 数组
     * void add(JsonElement element)：将 json 元素 element 添加到 json 数组中
     */
    @Test
    public void jsonArray3() {
        String json1 = "{\"id\":9527,\"name\":\"华安\",\"marry\":true}";
        String json2 = "{\"id\":1200,\"name\":\"安禄山\",\"marry\":false}";

        JsonObject jsonObject1 = new JsonParser().parse(json1).getAsJsonObject();
        JsonObject jsonObject2 = new JsonParser().parse(json2).getAsJsonObject();

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        System.out.println(jsonArray);
        //输出：[{"id":9527,"name":"华安","marry":true},{"id":1200,"name":"安禄山","marry":false}]
    }

    /**
     * JsonArray deepCopy()
     */
    @Test
    public void test4() {
        JsonArray jsonArray = new JsonParser().parse("[{\"code\":200,\"msg\":\"成功\"}]").getAsJsonArray();
        JsonArray deepCopy = jsonArray.deepCopy();
        deepCopy.add("Yes");
        System.out.println(jsonArray);//[{"code":200,"msg":"成功"}]
        System.out.println(deepCopy);//[{"code":200,"msg":"成功"},"Yes"]
    }



    /**
     * JsonElement get(int i): 返回数组的第i个元素
     * 1、如果 i 为负或大于等于数组的大小，则抛 IndexOutOfBoundsException 异常
     */
    @Test
    public void test5() {
        JsonArray jsonArray = new JsonParser().parse("[{\"code\":200,\"msg\":\"成功\"}]").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                //200,成功
                System.out.println(jsonObject.get("code").getAsString() + "," + jsonObject.get("msg").getAsString());
            }
        }
    }

    /**
     * BigDecimal getAsBigDecimal()：如果数组只包含单个元素，则可以将其作为{@link BigDecimal}获取
     * BigInteger getAsBigInteger()
     * boolean getAsBoolean()
     * byte getAsByte()
     * String getAsString()
     * ...其余类型都是同理，json 数组只能保持一个元素，否则取值抛异常.
     */
    @Test
    public void test6() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(45000.78);
        BigDecimal bigDecimal = jsonArray.getAsBigDecimal();
        System.out.println(bigDecimal.toString());//45000.78
    }

    /**
     * int size(): 返回数组中的元素个数
     * Iterator<JsonElement> iterator()：返回一个迭代器来导航数组的元素。由于数组是一个有序列表，迭代器按照元素插入的顺序导航元素。
     */
    @Test
    public void test7() {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"marry\":true},{\"id\":1200,\"name\":\"安禄山\",\"marry\":false}]";
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        System.out.println(jsonArray.size());//2
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonElement jsonElement = iterator.next();
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println(jsonObject);
        }
    }

    /**
     * JsonElement remove(int index):删除此数组中指定位置的元素。后续元素向左移动,返回从数组中删除的元素。
     * boolean remove(JsonElement element):从该数组中删除指定元素的第一个匹配项,如果数组不包含元素，则不做修改
     */
    @Test
    public void test8() {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"marry\":true},{\"id\":1200,\"name\":\"安禄山\",\"marry\":false}]";
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        jsonArray.remove(0);
        System.out.println(jsonArray);//[{"id":1200,"name":"安禄山","marry":false}]
    }

    /**
     * JsonElement set(int index, JsonElement element)
     * 1、将此数组中指定位置的元素替换为指定元素，element 不能为null，返回先前在指定位置的元素
     */
    @Test
    public void test9() {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"marry\":true},{\"id\":1200,\"name\":\"安禄山\",\"marry\":false}]";
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonArray.set(1, jsonObject);

        //[{"id":9527,"name":"华安","marry":true},{}]
        System.out.println(jsonArray);
    }

    /**
     * {@link JsonElement#isJsonNull()} ：验证此元素是否表示 null 值，是则返回 true
     * 1、特别注意下面这种情况，必须使用 此方法来判断，而不能使用 == null 来判断
     */
    @Test
    public void test10() {
        String json = "[\"本级小计\",368.00,328.00,]";
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            //当数组中的某个元素不存在时，则 jsonElement.isJsonNull() 就会为 true
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonNull()) {
                System.out.println(jsonElement.getAsString());
            } else {
                System.out.println("第 " + i + " 个元素只为 null。");
            }
        }
    }

    /**
     * JsonArray getAsJsonArray():将此元素作为 JsonArray 获取,如果元素是其他类型的元素，则会生成 IlleglastateException 异常,
     * 因此最好先调用 isJsonArray() 方法确保该元素是所需的类型，然后再使用此方法。
     */
    @Test
    public void test11() {
        String json = "{\"persons\":[{\"id\":9527,\"name\":\"华安\",\"marry\":true},{\"id\":1200,\"name\":\"安禄山\",\"marry\":false}]}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonElement persons = jsonObject.get("persons");
        if (persons.isJsonArray()) {
            JsonArray jsonArray = persons.getAsJsonArray();
            //{"id":1200,"name":"安禄山","marry":false}
            System.out.println(jsonArray.get(1));
        }
    }

    /**
     * JsonPrimitive getAsJsonPrimitive():
     * 将此元素作为 JsonPrimitive 获取，如果元素是其他类型的元素，则会引发 IlleglastateException 异常，
     * 因此最好先通过调用 isJsonPrimitive() 方法来确保该元素是所需的类型之后再使用此方法。JsonPrimitive 值可以是 Java 字符串、Java 基本数据类型及其包装器类型。
     */
    @Test
    public void test12() {
        String json = "[\"本级小计\",368.00,328.00,]";
        JsonElement sourceJsonElement = new JsonParser().parse(json);
        JsonArray jsonArray = sourceJsonElement.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (jsonPrimitive.isString()) {
                    System.out.println(jsonPrimitive.getAsString());
                } else if (jsonPrimitive.isNumber()) {
                    System.out.println(jsonPrimitive.getAsDouble());
                }
            }
        }
    }

}
