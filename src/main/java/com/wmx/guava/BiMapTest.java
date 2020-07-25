package com.wmx.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * BiMap 也叫双向映射，是一种保持其值和键的唯一性的映射，此约束使 BiMap 支持 "反向视图"，
 * 常用的实现有 {@link HashBiMap}
 * HashBiMap 由两个哈希表支持，键和值允许为 null，但是注意：key 必须唯一，value 也必须唯一，相当于 key 和 value 都是用 Set 存储一样。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 15:12
 */
public class BiMapTest {
    /**
     * 1、V put(@Nullable K key, @Nullable V value)
     * key 重复时，会覆盖旧值，如果给定值已绑定到此 biMap 中的其他键，则抛出 IllegalArgumentException。
     * 要避免此异常，请改为调用 {@link#forcePut}。
     * 2、BiMap<V, K> inverse()
     * 返回此 BiMap 的反向视图，key 与 value 调换
     */
    @Test
    public void HashBiMap1() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("张三", "2268461752@qq.com");
        biMap.put("李四", "7858461752@qq.com");
        //{张三=2268461752@qq.com, 李四=7858461752@qq.com}
        System.out.println(biMap);

        //7858461752@qq.com
        String s = biMap.get("李四");
        System.out.println(s);

        BiMap<String, String> inverse = biMap.inverse();
        //{2268461752@qq.com=张三, 7858461752@qq.com=李四}
        System.out.println(inverse);
        //{张三=2268461752@qq.com, 李四=7858461752@qq.com}
        System.out.println(biMap);
    }

    /**
     * V forcePut(@Nullable K key, @Nullable V value)
     * put的另一种形式，在继续 put 操作之前，它会静默地删除值为 value 的任何现有条目
     * void putAll(Map<? extends K, ? extends V> map)
     */
    @Test
    public void HashBiMap2() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("张三", "2268461752@qq.com");
        biMap.put("李四", "7858461752@qq.com");
        biMap.forcePut("王五", "2268461752@qq.com");
        //{李四=7858461752@qq.com, 王五=2268461752@qq.com}
        System.out.println(biMap);

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("马六", "12437484674567@163.com");
        hashMap.put("马大胆", "986768799989@163.com");

        biMap.putAll(hashMap);
        //{李四=7858461752@qq.com, 王五=2268461752@qq.com, 马六=12437484674567@163.com, 马大胆=986768799989@163.com}
        System.out.println(biMap);

        Set<String> values = biMap.values();
        //[7858461752@qq.com, 2268461752@qq.com, 12437484674567@163.com, 986768799989@163.com]
        System.out.println(values);
    }
}
