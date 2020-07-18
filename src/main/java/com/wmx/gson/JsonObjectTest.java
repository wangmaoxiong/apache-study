package com.wmx.gson;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * {@link JsonObject}
 * 1、表示  json 对象类型，由名称-值对组成，其中名称是字符串，此对象的成员元素按添加顺序进行维护。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/14 9:55
 */
@SuppressWarnings("all")
public class JsonObjectTest {

    /**
     * 手动 创建 JsonObject 对象 并添加属性，如果添加的 key 已经存在，则后面的会覆盖前面的
     * addProperty(String property, Number value)：添加数值类型属性
     * addProperty(String property, Boolean value)：添加布尔类型属性
     * addProperty(String property, Character value)：添加字符类型属性
     * addProperty(String property, String value)：添加字符串类型属性
     * add(String property, JsonElement value)：名称必须是字符串，但值可以是任意的 JsonElement
     */
    @Test
    public void jsonObject1() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pId", 9527);
        jsonObject.addProperty("pName", "华安");
        jsonObject.addProperty("isMarry", true);
        jsonObject.add("child", new JsonArray());
        System.out.println(jsonObject);//{"pId":9527,"pName":"华安","isMarry":true,"child":[]}
    }

    /**
     * 删除 JsonObject 属性
     * JsonElement remove(String property)
     * 1、删除 JsonObject 的属性，返回被删除的属性的值，原 JsonObject 会改变
     * 2、与 get 取值同理，如果 remove 的属性值不存在，则返回 null
     */
    @Test
    public void jsonObject2() {
        String json = "{\"pId\":9527,\"pName\":\"华安\",\"isMarry\":true}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String delProperty = jsonObject.remove("pName").getAsString();
        System.out.println("删除 " + delProperty + " 后：" + jsonObject);
        //输出：删除 华安 后：{"pId":9527,"isMarry":true}
    }

    /**
     * JsonElement get(String memberName)
     * 1、获取具有指定名称的 json 元素，如果 memberName 不存在，则返回 null
     * 2、JsonElement 是抽象类，它的子类是 JsonObject、JsonArray,JsonPrimitive、JsonNull
     * boolean has(String memberName)：检查此对象中是否存在具有指定名称的成员
     */
    @Test
    public void jsonObject3() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "孙悟空");
        jsonObject.addProperty("age", "");

        String name = jsonObject.get("name").getAsString();

        System.out.println(jsonObject.has("id"));//false
        System.out.println(jsonObject.get("id"));//null
        System.out.println(jsonObject.get("id") == null);//true
        System.out.println(name);//孙悟空
    }

    /**
     * JsonObject getAsJsonObject(String memberName)
     * 1、获取指定成员作为 JsonObject 的便利方法。
     */
    @Test
    public void jsonObject4() {
        String json = "{\"id\":1000,\"language\":{\"code\":\"001\",\"name\":\"汉语\"}}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject language = jsonObject.getAsJsonObject("language");
        System.out.println(language);//{"code":"001","name":"汉语"}
    }

    /**
     * JsonArray getAsJsonArray(String memberName)
     * 1、获取指定成员作为 JsonArray 的便利方法
     * 2、如果 json 对象属性的值是一个 json 数组，则可以使用此种返回获取
     */
    @Test
    public void jsonObject5() {
        String json = "{\"language\":[\"Java\",\"Android\",\"IOS\"]}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("language");
        System.out.println(jsonArray);//["Java","Android","IOS"]
    }

    /**
     * Set<Map.Entry<String, JsonElement>> entrySet()
     * 1、返回 json 对象的所有成员，集合是有序的，和元素的添加顺序一致.
     */
    @Test
    public void jsonObject6() {
        String json = "{\"pId\":9527,\"pName\":\"华安\",\"isMarry\":true}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> next = iterator.next();
            String key = next.getKey();
            JsonElement value = next.getValue();
            System.out.println(key + "=" + value);
        }
    }

    /**
     * int size():返回对象中键/值对的数目。
     * Set<String> keySet():返回所有成员的键值
     */
    @Test
    public void jsonObject7() {
        String json = "{\"pId\":9527,\"pName\":\"华安\",\"isMarry\":true}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println(jsonObject.size());
        Set<String> keySet = jsonObject.keySet();//3
        System.out.println(keySet);//[pId, pName, isMarry]
    }

}