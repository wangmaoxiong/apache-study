package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;

/**Spring-core 核心工具类 之 字符串工具类
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 8:24
 */
public class StringUtilsTest {

    /**
     * String[] addStringToArray(@Nullable String[] array, String str)
     * 1、往数组中添加元素，数组的大小是固定的，底层是使用 System.arraycopy 将旧数组复制到新数组，所以返回值是添加后的新数组
     * 2、array 等于 null 或者为空时，会自动创建
     */
    @Test
    public void addStringToArray() {
        String[] arr1 = null;
        String[] arr2 = new String[]{"基金", "联配"};

        arr1 = StringUtils.addStringToArray(arr1, "秦国");
        arr2 = StringUtils.addStringToArray(arr2, "护法");

        //[秦国]
        System.out.println(Arrays.asList(arr1));
        //[基金, 联配, 护法]
        System.out.println(Arrays.asList(arr2));
    }

    /**
     * String arrayToCommaDelimitedString(@Nullable Object[] arr)
     * 1、将数组转成字符串，元素之间默认使用 "," 连接, arr 为 null 或者为空时，返回空字符串
     * String arrayToDelimitedString(@Nullable Object[] arr, String delim)
     * 1、将数组转成字符串，并使用指定的字符串进行连接
     */
    @Test
    public void arrayToCommaDelimitedString() {
        String[] arr1 = new String[]{"秦", "汉", "大唐"};
        String[] arr2 = null;
        String delimitedString = StringUtils.arrayToCommaDelimitedString(arr1);
        //秦,汉,大唐
        System.out.println(delimitedString);
        System.out.println(StringUtils.arrayToCommaDelimitedString(arr2));

        String toDelimitedString = StringUtils.arrayToDelimitedString(arr1, "|");
        //秦|汉|大唐
        System.out.println(toDelimitedString);
    }

    /**
     * String capitalize(String str)
     * 1、将字符串的首字母转大写，如果 str 为null或者为空，则原样返回
     */
    @Test
    public void capitalize() {
        String s1 = "look you";
        String s2 = null;
        String s3 = "大秦万年";
        String capitalize1 = StringUtils.capitalize(s1);
        String capitalize2 = StringUtils.capitalize(s2);
        String capitalize3 = StringUtils.capitalize(s3);
        //Look you,null,大秦万年
        System.out.println(capitalize1 + "," + capitalize2 + "," + capitalize3);
    }

    /**
     * String collectionToCommaDelimitedString(@Nullable Collection<?> coll)
     * 1、将集合转为字符串，元素之间默认使用 "," 连接，如果 coll 为null或者为空，则返回空字符串
     * String collectionToDelimitedString(@Nullable Collection<?> coll, String delim)
     * 2、将集合转为字符串，元素之间使用指定字符串连接，如果 coll 为null或者为空，则返回空字符串
     */
    @Test
    public void collectionToCommaDelimitedString() {
        List<Integer> integerList = Arrays.asList(19, 20, 31, 45, 33, 34);
        String delimitedString = StringUtils.collectionToCommaDelimitedString(integerList);
        //19,20,31,45,33,34
        System.out.println(delimitedString);

        Set<String> stringSet = new HashSet<>();
        stringSet.add("秦国");
        stringSet.add("楚国");
        stringSet.add("韩国");
        String toDelimitedString = StringUtils.collectionToDelimitedString(stringSet, "','");
        //韩国','秦国','楚国
        System.out.println(toDelimitedString);
    }

    /**
     * String collectionToDelimitedString(@Nullable Collection<?> coll, String delim, String prefix, String suffix)
     * 1、将集合（coll）转为字符串，使用指定字符串（delim）连接元素
     * 2、prefix、suffix 是连接元素时使用前缀与后缀，源码：sb.append(prefix).append(item.next()).append(suffix);
     * 3、所以非常适合用于连接数据库 in 函数的参数，如： in('99pp','887uy','67tf')
     * 4、如果 coll 为null或者为空，则返回空字符串
     */
    @Test
    public void collectionToDelimitedString2() {
        List<String> stringList = Arrays.asList("巨款", "考虑", "环境", "电费");
        String delimitedString = StringUtils.collectionToDelimitedString(stringList, ",", "'", "'");
        //'巨款','考虑','环境','电费'
        System.out.println(delimitedString);
    }

    /**
     * Set<String> commaDelimitedListToSet(@Nullable String str)
     * 1、将字符串转为 Set，元素使用英文","符号隔开，如果 str 为 null，则返回空集合
     * 2、返回的集合为 LinkedHashSet
     */
    @Test
    public void commaDelimitedListToSet() {
        String s1 = "秦,汉,元,明,清,汉";
        String s2 = null;
        Set<String> stringSet = StringUtils.commaDelimitedListToSet(s1);
        //[秦, 汉, 元, 明, 清]
        System.out.println(stringSet);
        //[]
        System.out.println(StringUtils.commaDelimitedListToSet(s2));
    }

    /**
     * String[] commaDelimitedListToStringArray(@Nullable String str)
     * 1、将字符串转为 数组，元素使用英文","符号隔开，如果 str 为 null，则返回空数组
     * String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter)
     * 1、将字符串转为 数组，元素使用指定字符串符号隔开，如果 str 为 null，则返回空数组
     */
    @Test
    public void commaDelimitedListToStringArray() {
        String s1 = "秦,汉,元,明,清,汉";
        String s2 = null;
        String[] strings = StringUtils.commaDelimitedListToStringArray(s1);
        String[] strings1 = StringUtils.commaDelimitedListToStringArray(s2);
        //[秦, 汉, 元, 明, 清, 汉]
        System.out.println(Arrays.asList(strings));
        //[]
        System.out.println(Arrays.asList(strings1));

        String s3 = "秦|汉|元|明|清|汉";
        String[] strings2 = StringUtils.delimitedListToStringArray(s3, "|");
        //[秦, 汉, 元, 明, 清, 汉]
        System.out.println(Arrays.asList(strings2));

    }

    /**
     * String[] concatenateStringArrays(@Nullable String[] array1, @Nullable String[] array2)
     * 1、将两个数组的元素合二为一，成为一个新数组。
     * 2、源码的思路是：如果 array1 为空或者为 null，则直接返回 array2，如果 array2 为空或者为null，则直接返回 array1
     * 最后使用 System.arraycopy 的方式复制新数组
     */
    @Test
    public void concatenateStringArrays() {
        String[] arr1 = new String[]{"19", "户库", "吏部"};
        String[] arr2 = new String[]{"放假", "端口", "登陆"};
        String[] stringArrays = StringUtils.concatenateStringArrays(arr1, arr2);
        //[19, 户库, 吏部, 放假, 端口, 登陆]
        System.out.println(Arrays.asList(stringArrays));
    }

    /**
     * boolean containsWhitespace(@Nullable CharSequence str)
     * boolean containsWhitespace(@Nullable String str)
     * 1、检查 str 是否含有空格，空字符串与 null 不算空格
     */
    @Test
    public void containsWhitespace() {
        String[] strings = new String[]{" 中国", "中 国", "", " ", null};
        //true,true,false,true,false
        System.out.print(StringUtils.containsWhitespace(strings[0]) + ",");
        System.out.print(StringUtils.containsWhitespace(strings[1]) + ",");
        System.out.print(StringUtils.containsWhitespace(strings[2]) + ",");
        System.out.print(StringUtils.containsWhitespace(strings[3]) + ",");
        System.out.print(StringUtils.containsWhitespace(strings[4]));
    }

    /**
     * int countOccurrencesOf(String str, String sub)
     * 1、检查源字符串（str） 中字符串（sub）出现的次数，如果 str 或者 sub 任意一个为空或者为null，则返回0
     */
    @Test
    public void countOccurrencesOf() {
        String s1 = "培训方案 “3、培训内容”要写本系统自己的，不能用一体化系统的培训内容。";
        int i = StringUtils.countOccurrencesOf(s1, "系统");
        int i1 = StringUtils.countOccurrencesOf(s1, "培训");
        //2, 3
        System.out.println(i + ", " + i1);
    }

    /**
     * String getFilename(@Nullable String path)
     * 1、获取字符串中的文件名称，如 "mypath/myfile.txt" -> "myfile.txt"
     * 2、如果 path 为 null，则返回 null。路径必须是左斜杠，源码是 path.lastIndexOf("/") 分割取值
     */
    @Test
    public void getFilename() {
        String s1 = "C:\\wmx\\temp\\git\\MyDocument\\README.md";
        String s2 = "C:/wmx/temp/git/MyDocument/README.md";
        String filename1 = StringUtils.getFilename(s1);
        String filename2 = StringUtils.getFilename(s2);
        //C:\wmx\temp\git\MyDocument\README.md, README.md
        System.out.println(filename1 + ", " + filename2);
    }

    /**
     * String getFilenameExtension(@Nullable String path)
     * 1、获取文件格式，如 "mypath/myfile.txt" -> "txt"。
     * 2、源码 path.lastIndexOf(".") 分割取值
     */
    @Test
    public void getFilenameExtension() {
        String s1 = "C:\\wmx\\temp\\git\\MyDocument\\README.md";
        String s2 = "C:/wmx/temp/git/MyDocument/README.md";
        String s3 = "C:/wmx/temp/git/MyDocument";
        //md, md, null
        System.out.print(StringUtils.getFilenameExtension(s1) + ", ");
        System.out.print(StringUtils.getFilenameExtension(s2) + ", ");
        System.out.print(StringUtils.getFilenameExtension(s3));
    }

    /**
     * boolean hasLength(@Nullable CharSequence str)
     * boolean hasLength(@Nullable String str)
     * 1、检查字符串是否有长度， str为null或者为空，返回false，否则为true，空格也算有长度
     */
    @Test
    public void hasLength() {
        String[] strings = new String[]{"", " ", null, "开"};
        //false, true, false, true
        System.out.print(StringUtils.hasLength(strings[0]) + ", ");
        System.out.print(StringUtils.hasLength(strings[1]) + ", ");
        System.out.print(StringUtils.hasLength(strings[2]) + ", ");
        System.out.print(StringUtils.hasLength(strings[3]));
    }

    /**
     * boolean hasText(@Nullable CharSequence str)
     * boolean hasText(@Nullable String str)
     * 1、检查 str 是否有文本值，空格不算文本，所以 str 为null或者为空，或者空格，都返回 false
     */
    @Test
    public void hasText() {
        String[] strings = new String[]{"", " ", null, "开"};
        //false, false, false, true
        System.out.print(StringUtils.hasText(strings[0]) + ", ");
        System.out.print(StringUtils.hasText(strings[1]) + ", ");
        System.out.print(StringUtils.hasText(strings[2]) + ", ");
        System.out.print(StringUtils.hasText(strings[3]));
    }

    /**
     * boolean isEmpty(@Nullable Object str)
     * 1、判断对象是否为空，或者为nul，源码：(str == null || "".equals(str))
     */
    @Test
    public void isEmpty() {
        String s = "";
        Map<String, String> map = new HashMap<>();
        List<Object> objects = Arrays.asList();
        //true, false, false
        System.out.print(StringUtils.isEmpty(s) + ", ");
        System.out.print(StringUtils.isEmpty(map) + ", ");
        System.out.print(StringUtils.isEmpty(objects));
    }

    /**
     * String[] sortStringArray(String[] array)
     * 1、对数组的中元素进行排序，如果 array 为null或者为空，则返回空数组
     * 2、数字在前，单词次之，汉字最末，根据 ascii 码排序，底层用的 Arrays.sort(array)
     */
    @Test
    public void sortStringArray() {
        String[] strings = new String[]{"Process", "finished", "with", "exit", "code", "秦国", "345", "23"};
        String[] stringArray = StringUtils.sortStringArray(strings);
        //[345, Process, code, exit, finished, with, 秦国]
        System.out.println(Arrays.asList(stringArray));
    }

    /**
     * String[] toStringArray(Collection<String> collection)
     * String[] toStringArray(Enumeration<String> enumeration)
     * 1、将 {@link Collection}、{@link Enumeration} 转为数组
     */

    /**
     * String trimAllWhitespace(String str)：去掉 str 中的所有空格
     * String[] trimArrayElements(String[] array)：去掉数组中所有元素前后的空格
     */
    @Test
    public void trimArrayElements() {
        String[] strings = new String[]{"", " ", null, " 开 ", " 考  虑 "};
        String[] trimArrayElements = StringUtils.trimArrayElements(strings);
        //[, , null, 开, 考  虑]
        System.out.println(Arrays.asList(trimArrayElements));
    }
}
