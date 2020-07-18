package com.wmx.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * {@link JsonParser} 将 Json 字符内容解析为  json 元素 {@link JsonElement}
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/14 9:57
 */
@SuppressWarnings("all")
public class JsonParserTest {

    /**
     * JsonElement parse(String json)
     * 1、将指定的 JSON 字符串解析为 json 元素，如果被解析的字符串不符合 json 格式，则抛出异常
     * 2、JsonElement 是抽象类，它的子类是 JsonObject、JsonArray,JsonPrimitive、JsonNull
     */
    @Test
    public void test1() {
        String json = "{\"id\":1000,\"name\":\"华安\",\"birthday\":\"Jul 13, 2020 8:46:42 PM\",\"marry\":true}";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject);//{"id":1000,"name":"华安","birthday":"Jul 13, 2020 8:46:42 PM","marry":true}
    }

    /**
     * JsonElement parse(String json)
     * Json 格式的数组字符串 转为 JSON 数组对象
     */
    @Test
    public void test2() {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Jul 13, 2020 8:46:18 PM\",\"marry\":true},{\"id\":8866,\"name\":\"宁王\",\"marry\":false}]";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        System.out.println(jsonArray.get(1));//{"id":8866,"name":"宁王","marry":false}
    }

    /**
     * JsonElement parse(Reader json)
     * 1、解析字符输入流的内容为 json 元素，即可以之间文件中的内容解析为 json 元素
     * 2、文件中的 json 内容可以是压缩过的，也可以是格式化后的
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        String json = "[{\"id\":9527,\"name\":\"华安\",\"birthday\":\"Jul 13, 2020 8:46:18 PM\",\"marry\":true},{\"id\":8866,\"name\":\"宁王\",\"marry\":false}]";

        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File homeDirectory = fileSystemView.getHomeDirectory();
        File jsonFile = new File(homeDirectory, "test.json");

        if (!jsonFile.exists()) {
            Writer writer = new FileWriter(jsonFile);
            writer.write(json);
            writer.flush();
            writer.close();
            System.out.println("新建文件：" + jsonFile.getAbsolutePath());
        }

        Reader reader = new FileReader(jsonFile);
        JsonElement parse = new JsonParser().parse(reader);
        JsonArray jsonArray = parse.getAsJsonArray();
        //[{"id":9527,"name":"华安","birthday":"Jul 13, 2020 8:46:18 PM","marry":true},{"id":8866,"name":"宁王","marry":false}]
        System.out.println(jsonArray);
    }
}
