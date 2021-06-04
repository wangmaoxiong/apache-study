package com.wmx.jdk8;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 15:34
 */
public class Test {
    public static void main(String[] args) {

        ArrayList<String> arrayList = Lists.newArrayList("1", "2", "3", "4", "5", "6", "9", "73");
        System.out.println(getInFunctionParameter("id", arrayList, "t"));


    }

    public static String getInFunctionParameter(String key, List<String> values, String aliase) {
        StringBuffer result = new StringBuffer("(");
        aliase = StringUtils.isBlank(aliase) ? "" : aliase + ".";

        List<List<String>> partitions = Lists.partition(values, 4);
        int index = 0;
        for (List<String> partition : partitions) {
            String value = aliase + key + " in('" + StringUtils.join(partition, "','") + "')";
            if (index++ != partitions.size() - 1) {
                result.append(value).append(" or ");
            } else {
                result.append(value);
            }
        }
        if (result.length() == 1) {
            result.append("1=1");
        }
        result.append(")");
        return result.toString();
    }
}
