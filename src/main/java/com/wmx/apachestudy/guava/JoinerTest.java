package com.wmx.apachestudy.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Joiner 是连接器，用于连接 java.lang.Iterable、java.util.Iterator、java.lang.Object[] 的元素
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 15:28
 */
@SuppressWarnings("all")
public class JoinerTest {
    /**
     * Joiner on(String separator)
     * Joiner on(char separator) ：指定分隔符
     * <p>
     * String join(Iterable<?> parts)
     * String join(Iterator<?> parts)
     * String join(Object[] parts)：使用分隔符连接集合或者数组中的元素，返回字符串
     * <p>
     * Joiner skipNulls()：自动跳过 null 元素，否则 join 方法会抛空指针异常
     * Joiner useForNull(final String nullText) :对于 null 元素使用 nullText 值代替
     */
    @Test
    public void joiner1() {
        ArrayList<Integer> arrayList1 = Lists.newArrayList(100, 200, 300, null, 400);
        Joiner joiner1 = Joiner.on(",").skipNulls();
        String join1 = "(" + joiner1.join(arrayList1) + ")";
        //(100,200,300,400)
        System.out.println(join1);
    }

    @Test
    public void joiner2() {
        Object[] objects = new Object[]{"admin", "root", "apple", null, "huaWei"};
        String join2 = Joiner.on("','").useForNull("xxx").join(objects);
        join2 = "('" + join2 + "')";
        //('admin','root','apple','xxx','huaWei')
        System.out.println(join2);
    }

}