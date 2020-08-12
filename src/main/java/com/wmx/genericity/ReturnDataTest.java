package com.wmx.genericity;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/8/12 15:03
 */
public class ReturnDataTest {

    /**
     * 泛型方法测试
     */
    @Test
    public void test1() {
        ReturnData<String> returnData = new ReturnData<>("200", "成功", "7878UUY78809PL");
        System.out.println(returnData);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("token", "7878TY6767EF");

        ReturnData<List<Map<String, Object>>> listReturnData = new ReturnData<>();
        listReturnData.setCode("200");
        listReturnData.setMessage("操作成功！");
        listReturnData.setData(Arrays.asList(dataMap));
        System.out.println(listReturnData);
    }

}
