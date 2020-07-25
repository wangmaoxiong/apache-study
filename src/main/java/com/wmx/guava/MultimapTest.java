package com.wmx.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * {@link Multimap} 一个 key 对应多个 value.
 * 1、JDK 提供给的 Map 是一个键，一个值，一对一的，而实际开发中，经常遇到一个 KEY 多个 VALUE 的情况（比如一个分类下的书本），通常使用 Map<k,List<v>>
 * 2、Multimap 提供一个 key 对多个值.
 * 3、Multimap 的实现类有：ArrayListMultimap、HashMultimap、LinkedHashMultimap、TreeMultimap、ImmutableMultimap......
 * 4、操作上与 jdk 自带的 map 基本一样。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 14:25
 */
public class MultimapTest {

    /**
     * ArrayListMultimap 使用 ArrayList 来存储给定键的值，键和值可以为空，值是有顺序的。
     * 1、guava 所有的集合都有 create 静态方法用于创建实例
     * 2、boolean put(@Nullable K key, @Nullable V value)：添加键值对，key 可以重复
     * 3、Collection<V> get(@Nullable K key)：获取指定 key 的值，因为一对多，多以返回的是集合，key 不存在时，返回空集合。
     * 4、boolean remove(Object key, Object value)：
     * 从这个多重映射中移除一个键和值的单个键值对（如果存在），如果多重映射中的多个键值对符合此描述，则删除第一个
     * 5、Collection<V> removeAll(Object key): 返回已删除的值（可能为空），删除与键 key 关联的所有值。
     */
    @Test
    public void ArrayListMultimap1() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("name", "鲁迅");
        multimap.put("name", "周树人");
        multimap.put("name", "鲁迅");
        multimap.put("age", "45");
        //{name=[鲁迅, 周树人, 鲁迅], age=[45]}
        System.out.println(multimap);

        Collection<String> name = multimap.get("name");
        Collection<String> age = multimap.get("age");
        //[鲁迅, 周树人, 鲁迅]
        System.out.println(name);
        //[45]
        System.out.println(age);

        multimap.remove("name", "鲁迅");
        multimap.removeAll("age");
        //{name=[周树人, 鲁迅]
        System.out.println(multimap);
    }

    /**
     * HashMultimap
     */
    @Test
    public void hashMultimap1() {
        HashMultimap<String, Object> hashMultimap = HashMultimap.create();
        hashMultimap.put("id", 2003);
        hashMultimap.put("name", "张三");
        hashMultimap.put("name", "李四");
        //{name=[李四, 张三], id=[2003]}
        System.out.println(hashMultimap);

        Set<Map.Entry<String, Object>> entries = hashMultimap.entries();
        for (Map.Entry<String, Object> entry : entries) {
//            name = 李四
//            name = 张三
//            id = 2003
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
