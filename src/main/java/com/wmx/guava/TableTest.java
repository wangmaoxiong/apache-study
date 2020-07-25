package com.wmx.guava;


import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * {@link Table} 表格
 * 1、将一对有序键（称为行键和列键）与单个值相关联的 Map 集合，表可能是稀疏的，只有一小部分行键/列键对具有相应的值
 * 2、Table 涉及到 3 个概念：rowKey,columnKey,value，R 标识数据库航的主键，C 是列名，V 是 列的值。
 * 3、Table 接口有很多的实现，常用的有 ：{@link HashBasedTable}、{@link ArrayTable}、{@link TreeBasedTable} 等
 * 4、HashBasedTable 内部提供 Map<R, Map<C, V>> 结构用于存储数据库 table 结构的数据，此实现不同步，如果多个线程访问此表，必须在外部进行同步
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 10:44
 */
public class TableTest {
    /**
     * HashBasedTable 内部提供 Map<R, Map<C, V>> 结构用于存储数据库 table 结构的数据
     * 此实现不同步，如果多个线程访问此表，必须在外部进行同步
     */
    @Test
    public void hashBasedTable() {
        HashBasedTable<Object, Object, Object> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("1001", "name", "展护卫");
        hashBasedTable.put("1001", "age", "33");
        hashBasedTable.put("1001", "price", 88898.78);

        hashBasedTable.put("1002", "name", "马汉");
        hashBasedTable.put("1002", "age", "43");
        hashBasedTable.put("1002", "price", 56898.78);

        //获取每一行的主键：rowKeySet=[1001, 1002]
        Set<Object> rowKeySet = hashBasedTable.rowKeySet();
        System.out.println("rowKeySet=" + rowKeySet);

        //获取列名称：columnKeySet=[name, age, price]
        Set<Object> columnKeySet = hashBasedTable.columnKeySet();
        System.out.println("columnKeySet=" + columnKeySet);

        //hashBasedTable={1001={name=展护卫, age=33, price=88898.78}, 1002={name=马汉, age=43, price=56898.78}}
        System.out.println("hashBasedTable=" + hashBasedTable);

        //根据主键获取单条数据：row={name=展护卫, age=33, price=88898.78}
        Map<Object, Object> row = hashBasedTable.row("1001");
        System.out.println("row=" + row);

        //根据列名获取所有记录的值：name={1001=展护卫, 1002=马汉}
        Map<Object, Object> name = hashBasedTable.column("name");
        System.out.println("name=" + name);

        //根据主键以及列名获取字段值：name1=展护卫
        Object name1 = hashBasedTable.get("1001", "name");
        System.out.println("name1=" + name1);

        //遍历每个单元格
        Set<Table.Cell<Object, Object, Object>> cellSet = hashBasedTable.cellSet();
        for (Table.Cell<Object, Object, Object> cell : cellSet) {
            System.out.println("rowKey=" + cell.getRowKey() + " - " + cell.getColumnKey() + " - " + cell.getValue());
        }
    }

}