package com.wmx.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;

/**
 * {@link Gson API 测试}
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/13 20:27
 */
@SuppressWarnings("all")
public class GsonTest {
    /**
     * 基本类型的字符串 转 基本类型数据
     * T fromJson(String json, Class<T> classOfT)
     * 1、json：被解析的 json 格式的字符串
     * 2、classOfT：解析结果的类型，可以是基本类型，也可以是 POJO 对象类型等
     */
    @Test
    public void fromJson1() {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);
        double d = gson.fromJson("100.99", Double.class);
        boolean b = gson.fromJson("true", boolean.class);
        String s = gson.fromJson("爱你", String.class);
        System.out.printf("%s、%s、%s、%s%n", i, d, b, s);//100、100.99、true、爱你
    }

    /**
     * 数组格式的 Json 字符串 转 数组 对象
     * T fromJson(String json, Class<T> classOfT)
     */
    @Test
    public void fromJson2() {
        String jsonArrayStr = "[\"Java\",\"Android\",\"IOS\"]";
        Gson gson = new Gson();
        String[] strings = gson.fromJson(jsonArrayStr, String[].class);
        System.out.println(Arrays.asList(strings));//[Java, Android, IOS]
    }

    /**
     * 数组格式的 Json 字符串 转 List 列表
     * T fromJson(String json, Type typeOfT)
     * 1、将指定的 Json 反序列化为指定类的对象，如果指定的类是泛型类型，则使用 fromJson(String, Type)方法。
     * 3、json：被转换的 json 格式的字符串
     * 4、typeOfT：解析结果类型
     */
    @Test
    public void fromJson3() {
        String jsonArray = "[\"Java1\",\"Android2\",\"IOS3\"]";
        Gson gson = new Gson();
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {
        }.getType());
        System.out.println(stringList);//[Java1, Android2, IOS3]
    }

    /**
     * Json 字符串 -> 转为 POJO 对象
     * T fromJson(String json, Class<T> classOfT)
     * 1、json：被解析的 json 格式的字符串
     * 2、 classOfT：解析结果的类型，可以是基本类型，也可以是 POJO 对象类型，gson 会自动转换
     */
    @Test
    public void fromJson4() {
        /**符合 json 格式的字符串*/
        String personJson = "{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Nov 23, 2018 1:50:56 PM\",\"marry\":true}";
        Gson gson = new Gson();
        Person person = gson.fromJson(personJson, Person.class);
        System.out.println(person);
        //输出：Person{id=9527, name='华安', birthday=Fri Nov 23 13:50:56 CST 2018, marry=true}
    }

    /**
     * T fromJson(JsonElement json, Class<T> classOfT)
     * 1、将 json 元素转为 实体对象
     * 2、JsonElement 是抽象类，它的子类是 JsonObject、JsonArray,JsonPrimitive、JsonNull
     */
    @Test
    public void fromJson5() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 3000);
        jsonObject.addProperty("name", "王五");
        jsonObject.addProperty("marry", true);

        Gson gson = new Gson();
        Person person = gson.fromJson(jsonObject, Person.class);
        System.out.println(Arrays.asList(person));//[Person{id=3000, name='王五', birthday=null, marry=true}]
    }

    /**
     * T fromJson(Reader json, Class<T> classOfT)
     */
    @Test
    public void fromJson6() {
        try {
            String json = "{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Jul 14, 2020 8:32:19 AM\",\"marry\":true}";

            //1、在本地准备准备先生成一个 json 文件
            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
            File jsonFile = new File(homeDirectory, "wmx3.json");
            if (!jsonFile.exists()) {
                FileWriter fileWriter = new FileWriter(jsonFile);
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("输出 json 文件：" + jsonFile.getAbsolutePath());
            }
            Reader reader = new FileReader(jsonFile);
            //2、读取本地 json 文件的内容进行反序列化
            //Person{id=9527, name='华安', birthday=Tue Jul 14 08:32:19 CST 2020, marry=true}
            Person person = new Gson().fromJson(reader, Person.class);
            System.out.println(person);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 List 格式化为 json 字符串后，再反序列化为 List<Map<String,Object> 对象
     * 在开发中经常会遇到 将 List<POJO> 转为 List<Map<String,Object> 对象，或者将后者转为前者
     */
    @Test
    public void fromJson7(){
        String json = "[{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Jul 13, 2020 8:46:18 PM\",\"marry\":true},{\"id\":8866,\"name\":\"宁王\",\"marry\":false}]";

        List<Map<String,Object>> dataList = new Gson().fromJson(json,new TypeToken<List<Map<String,Object>>>() {}.getType());
        System.out.println(dataList);

        /**输出：
         * [{id=9527.0, name=华安, birthday=Jul 13, 2020 8:46:18 PM, marry=true},
         * {id=8866.0, name=宁王, marry=false}]
         */
    }


    /**
     * 基本类型 转 Json 字符串
     * String toJson(Object src)
     * 1、将对象转为 json 格式的字符串，如 基本数据、POJO 对象、以及 Map、List 等
     */
    @Test
    public void toJson1() {
        Gson gson = new Gson();
        String i = gson.toJson(100);
        String d = gson.toJson(100.99);
        String b = gson.toJson(false);
        String s = gson.toJson("Tiger");
        System.out.printf("%s、%s、%s、%s%n", i, d, b, s);//100、100.99、false、"Tiger"
    }

    /**
     * Java POJO 对象 转 json 字符串
     * String toJson(Object src)
     * 1、将对象转为 json 格式的字符串，如 基本数据、POJO 对象、以及 Map、List 等
     * 2、如果 POJO 对象某个属性的值为 null，则 toJson(Object src) 默认不会对它进行转化，结果字符串中不会出现此属性
     */
    @Test
    public void toJson2() {
        Person person = new Person();
        person.setId(1000);
        person.setName("华安");
        person.setBirthday(new Date());
        person.setMarry(true);

        Gson gson = new Gson();
        String jsonUser = gson.toJson(person);
        System.out.println(jsonUser);
        //输出：{"id":1000,"name":"华安","birthday":"Jul 13, 2020 8:46:42 PM","marry":true}
    }

    /**
     * List 集合 转 为 json 字符串,list 会被解析成 Json 数组，List 中的元素被解析成 json
     * String toJson(Object src)
     * 1、将对象转为 json 格式的字符，如 基本数据、POJO 对象、以及 Map、List 等
     * 2、如果 POJO 对象某个属性的值为 null，则 toJson(Object src) 默认不会对它进行转化，结果字符串中不会出现此属性
     */
    @Test
    public void toJson3() {
        Gson gson = new Gson();
        List<Person> dataList = this.getDataList();
        String personListJson = gson.toJson(dataList);
        System.out.println(personListJson);
        //输出：[{"id":9527,"name":"华安","birthday":"Jul 13, 2020 8:46:18 PM","marry":true},{"id":8866,"name":"宁王","marry":false}]
    }

    /**
     * String toJson(JsonElement jsonElement)
     * 1、将 json 元素转为 json 格式的字符串
     */
    @Test
    public void toJson4() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 3000);
        jsonObject.addProperty("name", "王五");
        jsonObject.addProperty("marry", true);

        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        String string = jsonObject.toString();//直接 toString 也行
        System.out.println(json);
        System.out.println(string);
    }

    /**
     * String toJson(Object src, Type typeOfSrc)
     * 1、此方法将指定的对象（包括泛型类型的对象）序列化到等效的 Json 字符串表示
     * 2、如果指定的对象是泛型，则必须使用此方法，对于非泛型对象，请改用 toJson(Object)
     */
    @Test
    public void toJson5() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 4000);
        dataMap.put("name", "马六");
        dataMap.put("birthday", new Date());
        dataMap.put("marry", false);

        Gson gson = new Gson();
        //因为不是泛型，所以可以直接使用 toJson(Object)
        String json = gson.toJson(dataMap, new TypeToken<Map<String, Object>>() {
        }.getType());
        //{"birthday":"Jul 14, 2020 9:10:00 AM","name":"马六","marry":false,"id":4000}
        System.out.println(json);
    }

    /**
     * JsonElement toJsonTree(Object src)
     * 1、将指定的对象（包括泛型类型的对象）序列化到等效表示为{@link JsonElement} json 元素对象
     * 2、src：要为其创建 JSON 元素的对象
     * 3、JsonElement 是抽象类，它的子类是 JsonObject、JsonArray,JsonPrimitive、JsonNull
     */
    @Test
    public void toJsonTree1() {
        //将 POJO 对象转为 Json 元素
        Person person = new Person(2000, "李四", new Date(), false);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(person);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        jsonObject.addProperty("salary", 12500.4F);
        System.out.println(jsonObject);
        //{"id":2000,"name":"李四","birthday":"Jul 14, 2020 8:55:00 AM","marry":false,"salary":12500.4}
    }

    /**
     * JsonElement toJsonTree(Object src, Type typeOfSrc)
     * 此方法将指定的对象（包括泛型类型的对象）序列化为{@link JsonElement} 等效表示形式。
     * 如果指定的对象是泛型类型，则必须使用此方法，对于非泛型对象，请改用 toJsonTree(Object)
     */
    @Test
    public void toJsonTree2() {
        List<Person> dataList = this.getDataList();
        Gson gson = new Gson();
        //因为不是泛型，则可以直接使用 JsonElement toJsonTree(Object src)
        JsonElement jsonElement = gson.toJsonTree(dataList, new TypeToken<List<Person>>() {
        }.getType());
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        System.out.println(jsonArray.get(0));
        //{"id":9527,"name":"华安","birthday":"Jul 14, 2020 8:32:19 AM","marry":true}
    }


    private List<Person> getDataList() {
        List<Person> personList = new ArrayList<>();

        Person person = new Person();
        person.setId(9527);
        person.setName("华安");
        person.setBirthday(new Date());
        person.setMarry(true);

        Person person2 = new Person();
        person2.setId(8866);
        person2.setName("宁王");

        personList.add(person);
        personList.add(person2);
        return personList;
    }

}