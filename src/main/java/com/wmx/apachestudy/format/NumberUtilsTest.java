package com.wmx.apachestudy.format;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/6/1 9:55
 */
public class NumberUtilsTest {

    public void test1() {
        BigDecimal bigDecimalFrom = null;
        BigDecimal bigDecimal = NumberUtils.toScaledBigDecimal(bigDecimalFrom);
        //0
        System.out.println(bigDecimal);
    }

    public static void main(String[] args) {
        NumberUtilsTest numberUtilsTest = new NumberUtilsTest();
        numberUtilsTest.test1();
    }
}
