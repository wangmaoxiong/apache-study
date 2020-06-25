package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Apache lang3 子项目下 NumberUtils 数值工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/6/1 9:55
 */
public class NumberUtilsTest {

    /**
     * NumberUtils 提供了数字类型的常量，-1、0、1，有 Long、Integer、Short、Byte、Double、Float 类型
     */
    @Test
    public void test1() {
        Integer integerZero = NumberUtils.INTEGER_ZERO;
        Float floatOne = NumberUtils.FLOAT_ONE;
        Double doubleMinusOne = NumberUtils.DOUBLE_MINUS_ONE;
        //0、1.0、-1.0
        System.out.printf("%s、%s、%s", integerZero, floatOne, doubleMinusOne);
    }

    /**
     * int compare(final byte x, final byte y)：比较 x、y 的大小，源码直接是 return x - y;
     * int compare(final int x, final int y): 比较 x、y 的大小，源码：x == y ? 0 : x < y ? -1 : 1
     * int compare(final long x, final long y): 比较 x、y 的大小，源码：x == y ? 0 : x < y ? -1 : 1
     * int compare(final short x, final short y) : 比较 x、y 的大小，源码：x == y ? 0 : x < y ? -1 : 1
     */
    @Test
    public void test2() {
        //-2、-1、-1、-1
        System.out.printf("%d、", NumberUtils.compare(Byte.parseByte("16"), Byte.parseByte("18")));
        System.out.printf("%d、", NumberUtils.compare(Integer.parseInt("16"), Integer.parseInt("18")));
        System.out.printf("%d、", NumberUtils.compare(Long.parseLong("16"), Long.parseLong("18")));
        System.out.printf("%d%n", NumberUtils.compare(Short.parseShort("16"), Short.parseShort("18")));
    }

    /**
     * BigInteger createBigInteger(final String str)
     * 1、将字符串转成 BigInteger，支持 10进制，十六进制（以0X或者#开头）、8进制（以0开头）
     * 2、如果 str 为 null，则返回 null。如果转换错误，则抛出 NumberFormatException
     */
    @Test
    public void test3() {
        BigInteger bigInteger1 = NumberUtils.createBigInteger("200");
        BigInteger bigInteger2 = NumberUtils.createBigInteger("0XC8");
        BigInteger bigInteger3 = NumberUtils.createBigInteger("0310");
        BigInteger bigInteger4 = NumberUtils.createBigInteger(null);
        //200、200、200、null
        System.out.printf("%s、%s、%s、%s", bigInteger1, bigInteger2, bigInteger3, bigInteger4);
    }

    /**
     * BigDecimal createBigDecimal(final String str)
     * 1、将字符串转成 BigDecimal，只支持 10 进制，底层就是使用 new BigDecimal(str)
     * 2、str 为null时，返回 null，str 为空时会抛出 NumberFormatException 异常
     */
    @Test
    public void test4() {
        BigDecimal bigDecimal1 = NumberUtils.createBigDecimal("200");
        BigDecimal bigDecimal2 = NumberUtils.createBigDecimal("300.677");
        BigDecimal bigDecimal3 = NumberUtils.createBigDecimal("1988876565677.99889");
        BigDecimal bigDecimal4 = NumberUtils.createBigDecimal(null);
        //200、300.677、1988876565677.99889、null
        System.out.printf("%s、%s、%s、%s", bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4);
    }

    /**
     * Double createDouble(final String str)：字符串转 Double 类型，源码： str == null ? null : Double.valueOf(str);
     * Float createFloat(final String str)：字符串转 Float 类型，源码： str == null ? null : Float.valueOf(str);
     * Integer createInteger(final String str)：
     * 1、字符串转 Integer 类型，支持十六进制(0xhhhh)、8进制(0dddd)，源码： str == null ? null : Integer.decode(str);
     * Long createLong(final String str)：
     * 1、字符串转 Long 类型，支持十六进制(0xhhhh)、8进制(0dddd)，源码： str == null ? null : Long.decode(str);
     */
    @Test
    public void test5() {
        //889888888.898767、8.766889E8、80000、8000、8766888819
        System.out.printf("%f、", NumberUtils.createDouble("889888888.8987667"));
        System.out.printf("%s、", NumberUtils.createFloat("876688881.898766181"));
        System.out.printf("%d、", NumberUtils.createInteger("80000"));
        System.out.printf("%d、", NumberUtils.createInteger("0X1F40"));
        System.out.printf("%d", NumberUtils.createLong("8766888819"));
    }

    /**
     * boolean isCreatable(final String str)
     * 1、判断字符串是否为有效的 java 数字，支持16进制、8进制、10进制、正数负数、科学计数法（如8.788006e+05）、类型限定符（110L、3.14f）
     * 2、0X 开头当做 16 进制处理，如 0X89F9；以0开头的非十六进制字符串作为八进制值处理，如 076、-076等
     * 3、注意例如 098 不是八进制，因为8进制是0-7，没有8、9，所以会当做10进制处理，而此时不是数字，所以为false.
     * 4、str 为空或者为 null，都返回 false
     */
    @Test
    public void test6() {
        //100=true、-110L=true、100.98=true、-110.88F=true、0X89F9=true
        System.out.printf("100=%b、", NumberUtils.isCreatable("100"));
        System.out.printf("110L=%b、", NumberUtils.isCreatable("110L"));
        System.out.printf("100.98=%b、", NumberUtils.isCreatable("100.98"));
        System.out.printf("-110.88F=%b、", NumberUtils.isCreatable("-110.88F"));
        System.out.printf("0X89F9=%b%n", NumberUtils.isCreatable("0X89F9"));

        //-076=true、098=false、8.788006e+05=true、1001.=true、1001.=false
        System.out.printf("-076=%b、", NumberUtils.isCreatable("-076"));
        System.out.printf("098=%b、", NumberUtils.isCreatable("098"));
        System.out.printf("8.788006e+05=%b、", NumberUtils.isCreatable("8.788006e+05"));
        System.out.printf("1001.=%b、", NumberUtils.isCreatable("1001."));
        System.out.printf("1001.=%b", NumberUtils.isCreatable("1001.x"));
    }

    /**
     * boolean isDigits(final String str)
     * 1、检查字符串中是否只含有数字，负数和小数都会返回 false
     * 2、str 为 null 或者为空 返回 false
     * 3、底层调用 {@link StringUtils#isNumeric(java.lang.CharSequence)}
     */
    @Test
    public void test7() {
        //w100=false、100=true、-100=false、100.0=false、0=true、null=false
        System.out.printf("w100=%b、", NumberUtils.isDigits("w100"));
        System.out.printf("100=%b、", NumberUtils.isDigits("100"));
        System.out.printf("-100=%b、", NumberUtils.isDigits("-100"));
        System.out.printf("100.0=%b、", NumberUtils.isDigits("100.0"));
        System.out.printf("0=%b、", NumberUtils.isDigits("0"));
        System.out.printf("null=%b", NumberUtils.isDigits(null));
    }

    /**
     * boolean isParsable(final String str)
     * 1、检查字符串是否可以解析为数字，即 {@link Integer#parseInt(String)},{@link Long#parseLong(String)}, {@link Float#parseFloat(String),{@link Double#parseDouble(String)}.
     * 2、这个方法可以防止调用上面的方法时出现  {@link java.text.ParseException}
     * 3、注意只支持 10 进制，支持正负数，不支持 8进制、16进制、不支持科学计数法，也不支持类型限定符（如 3000L，3.14F）
     */
    @Test
    public void test8() {
        System.out.printf("100=%b、", NumberUtils.isParsable("100"));
        System.out.printf("110L=%b、", NumberUtils.isParsable("110L"));
        System.out.printf("100.98=%b、", NumberUtils.isParsable("100.98"));
        System.out.printf("-110.88F=%b、", NumberUtils.isParsable("-110.88F"));
        System.out.printf("110.88F=%b、", NumberUtils.isParsable("110.88F"));
        System.out.printf("0X89F9=%b%n", NumberUtils.isParsable("0X89F9"));

        System.out.printf("-076=%b、", NumberUtils.isParsable("-076"));
        System.out.printf("098=%b、", NumberUtils.isParsable("098"));
        System.out.printf("8.788006e+05=%b、", NumberUtils.isParsable("8.788006e+05"));
        System.out.printf("1001.=%b、", NumberUtils.isParsable("1001."));
        System.out.printf("1001.=%b", NumberUtils.isParsable("1001.x"));
    }

    /**
     * 求最大值
     * byte max(byte a, final byte b, final byte c)
     * byte max(final byte... array)
     * double max(final double a, final double b, final double c)
     * double max(final double... array)
     * float max(final float a, final float b, final float c)
     * float max(final float... array)
     * int max(int a, final int b, final int c)
     * int max(final int... array)
     * long max(long a, final long b, final long c)
     * long max(final long... array)
     * short max(short a, final short b, final short c)
     * short max(final short... array)
     * <p>
     * 求最小值
     * byte min(byte a, final byte b, final byte c)
     * byte min(final byte... array)
     * double min(final double a, final double b, final double c)
     * double min(final double... array)
     * float min(final float a, final float b, final float c)
     * float min(final float... array)
     * int min(int a, final int b, final int c)
     * int min(final int... array)
     * long min(long a, final long b, final long c)
     * long min(final long... array)
     * short min(short a, final short b, final short c)
     * short min(final short... array)
     */
    @Test
    public void test9() {
        byte[] bytes = new byte[]{10, 22, 24, 32, 22, 52, 39, 32};
        float[] floats = new float[]{10.4f, 202.3f, 33.4f, 20.0f, 33f};
        long[] longs = new long[]{100L, 200L, 300L, 20L, 250L, 290L};

        //[10, 22, 24, 32, 22, 52, 39, 32]，max=52、min=10
        System.out.printf("%s，max=%s、min=%s%n", Arrays.toString(bytes), NumberUtils.max(bytes), NumberUtils.min(bytes));
        //[10.4, 202.3, 33.4, 20.0, 33.0]，max=202.3、min=10.4
        System.out.printf("%s，max=%s、min=%s%n", Arrays.toString(floats), NumberUtils.max(floats), NumberUtils.min(floats));
        //[100, 200, 300, 20, 250, 290]，max=300、min=20
        System.out.printf("%s，max=%s、min=%s", Arrays.toString(longs), NumberUtils.max(longs), NumberUtils.min(longs));
    }

    /**
     * byte toByte(final String str)：字符串转 byte，如果转换失败，异常捕获后不会抛出，直接返回默认值0；如果 str 为 null，则返回默认值 0
     * byte toByte(final String str, final byte defaultValue)：字符串转 byte，同时设置默认值时，其余和上面同理
     * <p>
     * 其它数值类型也是同理（转换失败不会抛出异常，而是返回默认值）
     * double toDouble(final BigDecimal value)：
     * double toDouble(final BigDecimal value, final double defaultValue)
     * double toDouble(final String str)
     * double toDouble(final String str, final double defaultValue)
     * float toFloat(final String str)
     * float toFloat(final String str, final float defaultValue)
     * int toInt(final String str):
     * long toLong(final String str)：
     * toInt(final String str, final int defaultValue)
     * long toLong(final String str, final long defaultValue)
     * short toShort(final String str)
     * toShort(final String str, final short defaultValue)
     */
    @Test
    public void test10() {
        //29、0、-30
        System.out.printf("%d、%d、%d%n", NumberUtils.toByte("29"), NumberUtils.toByte("x9"), NumberUtils.toByte("-30"));
        //39、45
        System.out.printf("%d、%d", NumberUtils.toByte("S30", (byte) 39), NumberUtils.toByte(null, (byte) 45));
    }

}
