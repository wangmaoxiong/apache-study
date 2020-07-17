package com.wmx.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * {@link ObjectMapper} 用于对 Java 对象（比如 POJO、List、Set、Map 等等）进行序列化与反序列化
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/16 14:34
 */
@SuppressWarnings("all")
public class ObjectMapperTest {
    /**
     * POJO 对象转 json 字符串
     * String writeValueAsString(Object value)
     * 1、该方法可用于将任何 Java 值、对象序列化为 json 字符串，如果对象中某个属性的值为 null，则默认也会序列化为 null
     * 2、value 为 null，返回 null
     *
     * @throws JsonProcessingException
     */
    @Test
    public void writeValueAsString1() throws JsonProcessingException {
        User user = new User(1000, "张三", new Date(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        //{"uId":1000,"uName":"张三","birthday":1594881355832,"price":null}
    }

    /**
     * String writeValueAsString(Object value)
     * 1、 Java 值、对象序列化为 json 字符串，value 可以是任意的 java 对象，比如 POJO、list、Map、Set 等等
     */
    @Test
    public void writeValueAsString2() {
        try {
            List<User> userList = new ArrayList<>(2);
            User user1 = new User(1000, "张三", null, 7777.88F);
            User user2 = new User(2000, "李四", new Date(), 9800.78F);
            userList.add(user1);
            userList.add(user2);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userList);
            //[{"uId":1000,"uName":"张三","birthday":null,"price":7777.88},{"uId":2000,"uName":"李四","birthday":1594882217908,"price":9800.78}]
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * String writeValueAsString(Object value)
     * 1、 Java 值、对象序列化为 json 字符串，value 可以是任意的 java 对象，比如 POJO、list、Map、Set 等等
     */
    @Test
    public void writeValueAsString3() throws JsonProcessingException {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("uId", 9527);
            dataMap.put("uName", "华安");
            dataMap.put("birthday", new Date());
            dataMap.put("price", 9998.45F);
            dataMap.put("marry", null);

            ObjectMapper objectMapper = new ObjectMapper();
            //{"birthday":1594884443501,"uId":9527,"uName":"华安","price":9998.45,"marry":null}
            String json = objectMapper.writeValueAsString(dataMap);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writeValue(File resultFile, Object value)
     * 1、 Java java 对象，比如 POJO、list、Map、Set 等等 序列化并输出到指定文件中
     * 2、文件不存在时，会自动新建
     */
    @Test
    public void writeValueAsString4() throws JsonProcessingException {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("uId", 9527);
            dataMap.put("uName", "华安2");
            dataMap.put("birthday", new Date());
            dataMap.put("price", 9998.45F);
            dataMap.put("marry", null);

            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
            File jsonFile = new File(homeDirectory, "wmx2.json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile, dataMap);
            System.out.println("输出 json 文件：" + jsonFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * T readValue(String content, Class<T> valueType)
     * 1、从给定的 JSON 内容字符串反序列化为对象
     * 2、content 为空或者为 null，都会报错
     * 3、valueType 表示反序列号的结果对象，可以是任何 java 对象，比如 POJO、List、Set、Map 等等.
     */
    @Test
    public void readValue1() {
        try {
            String json = "{\"uId\":1000,\"uName\":\"张三\",\"birthday\":1594881355832,\"price\":null}";
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(json, User.class);
            //User{uId=1000, uName='张三', birthday=Thu Jul 16 14:35:55 CST 2020, price=null}
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * T readValue(String content, Class<T> valueType)
     * 1、从给定的 JSON 内容字符串反序列化为对象
     * 2、content 为空或者为 null，都会报错
     * 3、valueType 表示反序列号的结果对象，可以是任何 java 对象，比如 POJO、List、Set、Map 等等.
     */
    @Test
    public void readValue2() {
        try {
            String json = "[{\"uId\":1000,\"uName\":\"张三\",\"birthday\":null,\"price\":7777.88},{\"uId\":2000,\"uName\":\"李四\",\"birthday\":1594882217908,\"price\":9800.78}]";
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> userList = objectMapper.readValue(json, List.class);
            //{uId=2000, uName=李四, birthday=1594882217908, price=9800.78}
            System.out.println(userList.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * T readValue(String content, Class<T> valueType)
     * 1、从给定的 JSON 内容字符串反序列化为对象
     * 2、content 为空或者为 null，都会报错
     * 3、valueType 表示反序列号的结果对象，可以是任何 java 对象，比如 POJO、List、Set、Map 等等.
     */
    @Test
    public void readValue3() {
        try {
            String json = "{\"birthday\":1594884443501,\"uId\":9527,\"uName\":\"华安\",\"price\":9998.45,\"marry\":null}";
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(json, Map.class);
            //{birthday=1594884443501, uId=9527, uName=华安, price=9998.45, marry=null}
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * T readValue(File src, Class<T> valueType)：将本地 json 内容文件反序列为 Java 对象
     */
    @Test
    public void readTree4() {
        try {
            String json = "[{\"uId\":1000,\"uName\":\"张三\",\"birthday\":null,\"price\":7777.88},{\"uId\":2000,\"uName\":\"李四\",\"birthday\":1594882217908,\"price\":9800.78}]";
            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
            File jsonFile = new File(homeDirectory, "wmx.json");
            if (!jsonFile.exists()) {
                FileWriter fileWriter = new FileWriter(jsonFile);
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("输出 json 文件：" + jsonFile.getAbsolutePath());
            }
            List<User> userList = new ObjectMapper().readValue(jsonFile, List.class);
            //{uId=2000, uName=李四, birthday=1594882217908, price=9800.78}
            System.out.println(userList.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JsonNode readTree(String content)：将 JSON 内容反序列化为 JsonNode 对象
     *
     * @throws IOException
     */
    @Test
    public void readTree1() throws IOException {
        //被解析的 json 格式的字符串
        String json = "{\"notices\":[{\"title\":\"停水\",\"day\":\"1\"},{\"title\":\"停电\",\"day\":\"3\"},{\"title\":\"停网\",\"day\":\"2\"}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode);
    }

    /**
     * JsonNode readTree(URL source) :对网络上的 json 文件进行反序列化为 json 节点对象
     */
    @Test
    public void readTree2() {
        try {
            URL url = new URL("http://t.weather.sojson.com/api/weather/city/101030100");
            JsonNode jsonNode = new ObjectMapper().readTree(url);
            System.out.println(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JsonNode readTree(File file)：将本地 json 文件反序列化为 json 节点对象
     */
    @Test
    public void readTree3() {
        try {
            String json = "[{\"uId\":1000,\"uName\":\"张三\",\"birthday\":null,\"price\":7777.88},{\"uId\":2000,\"uName\":\"李四\",\"birthday\":1594882217908,\"price\":9800.78}]";
            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
            File jsonFile = new File(homeDirectory, "wmx.json");
            if (!jsonFile.exists()) {
                FileWriter fileWriter = new FileWriter(jsonFile);
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("输出 json 文件：" + jsonFile.getAbsolutePath());
            }
            JsonNode jsonNode = new ObjectMapper().readTree(jsonFile);
            System.out.println(jsonNode.get(0));//{"uId":1000,"uName":"张三","birthday":null,"price":7777.88}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * T treeToValue(TreeNode n, Class<T> valueType)：json 节点对象转 Java 对象（如 POJO、List、Set、Map 等等）
     */
    @Test
    public void test() {
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
     * T convertValue(Object fromValue, Class<T> toValueType)
     * 1、将 Java 对象（如 POJO、List、Map、Set 等）序列化为 Json 节点对象，通常有以下两种方式：
     * 2、一种方式是先序列化为 json 字符串，然后 readTree 反序列化为 Json 节点
     * 3、还有就是使用此种方式进行转换，将 java 对象直接转换为 json 节点。
     */
    @Test
    public void convertValue1() {
        User user = new User(1000, "张三", new Date(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(user, JsonNode.class);
        //{"uId":1000,"uName":"张三","birthday":1594967015825,"price":null}
        System.out.println(jsonNode);
    }

    @Test
    public void convertValue2() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("uId", 9527);
        dataMap.put("uName", "华安");
        dataMap.put("birthday", new Date());
        dataMap.put("price", 9998.45F);
        dataMap.put("marry", null);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode objectNode = objectMapper.convertValue(dataMap, JsonNode.class);
        //{"birthday":1594950930586,"uId":9527,"uName":"华安","price":9998.45,"marry":null}
        System.out.println(objectNode);
    }

    @Test
    public void convertValue3() {
        List<User> userList = new ArrayList<>(2);
        User user1 = new User(1000, "张三", null, 7777.88F);
        User user2 = new User(2000, "李四", new Date(), 9800.78F);
        userList.add(user1);
        userList.add(user2);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode objectNode = objectMapper.convertValue(userList, JsonNode.class);
        //[{"uId":1000,"uName":"张三","birthday":null,"price":7777.88},{"uId":2000,"uName":"李四","birthday":1594967168878,"price":9800.78}]
        System.out.println(objectNode);
    }

}
