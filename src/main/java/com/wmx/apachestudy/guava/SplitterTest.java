package com.wmx.apachestudy.guava;

import com.google.common.base.Splitter;
import org.junit.Test;

/**
 * Splitter 是分割器，用于分割字符序列 java.lang.CharSequence
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 15:32
 */
public class SplitterTest {
    /**
     * Splitter on(Pattern separatorPattern)：使用 Java 正则表达式分割
     * Splitter on(final String separator)：指定分割字符串
     * Splitter on(char separator)：指定分割字符
     * <p>
     * Splitter trimResults()：对拆分后的单个结果去除前后空格
     * Splitter omitEmptyStrings() ：省略空字符串，即对于空元素不再返回。
     * Iterable<String> split(final CharSequence sequence)：拆分字符串，返回 Iterable
     */
    @Test
    public void test1() {
        String text = "('admin','root','apple',null ,, ,'hua Wei')";
        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
        Iterable<String> stringIterable = splitter.split(text);
//        ('admin'
//        'root'
//        'apple'
//        null
//        'hua Wei')
        stringIterable.forEach(item -> System.out.println(item));
    }

    /**
     * Splitter fixedLength(final int length)：定长分割，每 length 个字符作为一组
     * List<String> splitToList(CharSequence sequence)：
     */
    @Test
    public void test2() {
        String message = "Splitter是分割器，通常我们会把它们定义为static final";
        Splitter fixedLength = Splitter.fixedLength(8).trimResults().omitEmptyStrings();
        Iterable<String> iterable = fixedLength.split(message);
//         Splitter
//         是分割器，通常我
//         们会把它们定义为
//         static f
//         inal
        iterable.forEach(item -> System.out.println(item));
    }
}
