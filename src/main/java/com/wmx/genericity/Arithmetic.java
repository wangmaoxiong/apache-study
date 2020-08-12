package com.wmx.genericity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 泛型通配符练习
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/8/12 15:16
 */
public class Arithmetic {

    /**
     * 加法运算，为了数据安全，防止数据精度溢出，全部转换为 BigDecimal 后进行运算
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public <T extends Number> T add(T a, T b) {
        BigDecimal bigDecimal1 = new BigDecimal(a.toString());
        BigDecimal bigDecimal2 = new BigDecimal(b.toString());
        BigDecimal add = bigDecimal1.add(bigDecimal2);
        if (a instanceof Integer) {
            return (T) Integer.valueOf(add.toString());
        }
        if (a instanceof Long) {
            return (T) Long.valueOf(add.toString());
        }
        if (a instanceof Float) {
            return (T) Float.valueOf(add.toString());
        }
        if (a instanceof Double) {
            return (T) Double.valueOf(add.toString());
        }
        if (a instanceof BigDecimal) {
            return (T) add;
        }
        if (a instanceof BigInteger) {
            return (T) add.toBigInteger();
        }
        throw new IllegalArgumentException("参数类型不支持：" + a.getClass().getTypeName());
    }

    public static void main(String[] args) {
        //0.30000000000000004（数据精度溢出）
        System.out.println(0.1 + 0.2);
        //下面的都是安全的
        System.out.println(new Arithmetic().add(0.1, 0.2));
        System.out.println(new Arithmetic().add(23, 3455));
        System.out.println(new Arithmetic().add(54.5623, 3455.4546));
        System.out.println(new Arithmetic().add(23.56F, 3455.5767F));
        System.out.println(new Arithmetic().add(236768L, 345567L));
    }

}
