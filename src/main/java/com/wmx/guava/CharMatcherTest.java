package com.wmx.guava;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * CharMatcher 字符匹配器，用于匹配字符,只要灵活使用，可以发挥很大的作用
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 16:01
 */
public class CharMatcherTest {
    /**
     * CharMatcher is(final char match)：表示等于
     * CharMatcher or(CharMatcher other)：表示或者
     * CharMatcher inRange(final char startInclusive, final char endInclusive)：表示 in，在某个范围内
     * CharMatcher and(CharMatcher other)：表示并且
     * CharMatcher isNot(final char match)：表示不等于
     * CharMatcher any()：表示任意字符
     * <p>
     * boolean matches(char c): 匹配给定字符
     */
    @Test
    public void test1() {
        //表示匹配字符 ',' 或者字符 '|' 或者字符 '_'
        CharMatcher charMatcher = CharMatcher.is(',').or(CharMatcher.is('|')).or(CharMatcher.is('_'));
        boolean matches1 = charMatcher.matches(',');
        boolean matches2 = charMatcher.matches('|');
        boolean matches3 = charMatcher.matches('_');
        boolean matches4 = charMatcher.matches('#');
        //true,true,true,false
        System.out.println(matches1 + "," + matches2 + "," + matches3 + "," + matches4);
    }

    @Test
    public void test2() {
        //表示匹配 [a,c] 之间的字符，但是不能等于 'b'
        CharMatcher matcher = CharMatcher.inRange('a', 'c').and(CharMatcher.isNot('b'));
        boolean a = matcher.matches('a');
        boolean b = matcher.matches('b');
        boolean c = matcher.matches('c');
        //true,false,true
        System.out.println(a + "," + b + "," + c);

    }

    /**
     * int countIn(CharSequence sequence)：获取字符序列中匹配的次数
     */
    @Test
    public void test3() {
        //判断字符是否是数字
        CharMatcher matcher1 = CharMatcher.inRange('0', '9');
        //判断字符是字母
        CharMatcher matcher2 = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        //true,false
        System.out.println(matcher1.matches('7') + "," + matcher1.matches('p'));
        //true,false
        System.out.println(matcher2.matches('f') + "," + matcher2.matches(','));

        //获取 "abc123sdk897" 字符串中数字出现的次数
        int countIn = matcher1.countIn("abc123sdk897");
        //6
        System.out.println(countIn);
    }

    /**
     * String trimFrom(CharSequence sequence)：去掉字符串序列中前后被匹配的字符
     * String trimLeadingFrom(CharSequence sequence)：去掉头部匹配的字符
     * String trimTrailingFrom(CharSequence sequence)：去掉尾部匹配的字符
     */
    @Test
    public void test4() {
        //去掉字符串前后的 \r、\n、\t 以及空格字符
        CharMatcher matcher3 = CharMatcher.is('\t').or(CharMatcher.is('\r')).or(CharMatcher.is('\n')).or(CharMatcher.is(' '));
        String text3 = "\r Solr Home \n ";
        String trimFrom = matcher3.trimFrom(text3);
        //Solr Home
        System.out.println(trimFrom);
    }

    /**
     * String removeFrom(CharSequence sequence)：删除字符串中所有匹配的字符
     */
    @Test
    public void test5() {
        CharMatcher matcher2 = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        //删除 "abc123SDK897" 字符串中所有的字母
        String removeFrom = matcher2.removeFrom("abc123SDK897");
        //123897
        System.out.println(removeFrom);
    }

    /**
     * String replaceFrom(CharSequence sequence, CharSequence replacement)：使用 replacement 替换 sequence 中匹配的字符
     * CharMatcher anyOf(final CharSequence sequence): 表示 sequence 字符序列中的任意字符
     */
    @Test
    public void test6() {
        CharMatcher matcher4 = CharMatcher.is('淫').or(CharMatcher.is('毒'));
        String text4 = "卖淫团伙同时也是一个贩毒组织。";
        String replaceFrom = matcher4.replaceFrom(text4, "xx");
        //卖xx团伙同时也是一个贩xx组织。
        System.out.println(replaceFrom);

        //剔除目标字符串中的所有数字字符
        CharMatcher matcher5 = CharMatcher.anyOf("0123456789");
        String replace = matcher5.replaceFrom("嬴政一统六国221", "");
        //嬴政一统六国
        System.out.println(replace);
    }

    /**
     * CharMatcher ascii()：获取 ascii 码的字符匹配器。对照表：http://ascii.911cha.com/
     */
    @Test
    public void test7() {
        //获取目标字符串中 ascii 码的字符个数（汉字不属于 ascii 码之内）
        CharMatcher ascii = CharMatcher.ascii();
        int count = ascii.countIn("12abn_)*77%三国");
        //11
        System.out.println(count);
    }

    /**
     * CharMatcher whitespace()：获取空白字符匹配器，包含 \r、\n、\t 等字符
     */
    @Test
    public void test8() {
        //去掉目标字符串中所有空格
        CharMatcher whitespace = CharMatcher.whitespace();
        String text6 = " 一统 天 下 \r\n\t";
        String replace1 = whitespace.replaceFrom(text6, "");
        //|一统天下|
        System.out.println("|" + replace1 + "|");
    }

    /**
     * boolean matchesAllOf(CharSequence sequence):
     * 1、为 sequence 的每个字符调用 matches(char c)方法，直到返回 false 或到达结尾返回 true.
     */
    @Test
    public void test9() {
        //判断数字
        CharMatcher matcher1 = CharMatcher.inRange('0', '9');
        boolean matchesAllOf1 = matcher1.matchesAllOf("75847584758");
        boolean matchesAllOf2 = matcher1.matchesAllOf("75847584.898");
        //true
        System.out.println(matchesAllOf1);
        //false
        System.out.println(matchesAllOf2);
    }

    /**
     * CharMatcher anyOf(final CharSequence sequence): 表示 sequence 字符序列中的任意字符
     * boolean matchesAnyOf(CharSequence sequence)：如果字符序列中至少包含一个匹配的字符，则返回 true
     */
    @Test
    public void test10() {
        //判断数字
        CharMatcher matcher1 = CharMatcher.anyOf("中国|韩国|日本");
        boolean matchesAllOf1 = matcher1.matchesAnyOf("中国上下五千年");
        boolean matchesAllOf2 = matcher1.matchesAnyOf("日本遣唐使");
        boolean matchesAllOf3 = matcher1.matchesAnyOf("朝鲜古称高丽");
        //true
        System.out.println(matchesAllOf1);
        //false
        System.out.println(matchesAllOf2);
        //false
        System.out.println(matchesAllOf3);
    }

    /**
     * boolean matchesNoneOf(CharSequence sequence):
     * 如果字符序列不包含匹配的字符，则返回 true，相当于 !matchesAnyOf(sequence)
     */
    @Test
    public void test11() {
        //判断数字
        CharMatcher matcher1 = CharMatcher.anyOf("中国|韩国|日本");
        boolean matchesAllOf1 = matcher1.matchesNoneOf("中国上下五千年");
        boolean matchesAllOf2 = matcher1.matchesNoneOf("日本遣唐使");
        boolean matchesAllOf3 = matcher1.matchesNoneOf("朝鲜古称高丽");
        //false
        System.out.println(matchesAllOf1);
        //false
        System.out.println(matchesAllOf2);
        //true
        System.out.println(matchesAllOf3);
    }

}
