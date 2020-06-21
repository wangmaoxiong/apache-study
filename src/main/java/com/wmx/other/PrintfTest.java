package com.wmx.other;


import org.junit.Test;

import java.util.Date;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 17:08
 * <p>
 * PrintStream printf(String format, Object ... args)
 * format：表示输出的格式
 * args：表示待输出的数据对象
 * </p>
 */
public class PrintfTest {

    /**
     * %s ：格式化字符串输出，严格来说可以将任意类型都作为字符串形式输出
     */
    @Test
    public void test1() {

        /**
         * %s：表示以字符串(String)形式输出
         * %S ：表示将字符串以大写形式输出
         * %n：表示换行
         */
        //1212
        System.out.printf("%s%n", new Integer(1212));

        //可以支持多个参数，%s、%S 相当于参数的占位符，参数值按顺序填入
        Integer id = 990;
        String name = "张三丰WuDang";
        //id=990,name=张三丰WUDANG
        System.out.printf("id=%s,name=%S%n", id, name);

        //可以在%s之间插入变量编号，1$表示第一个参数，2$表示第2个参数，以此类推
        //id=990，name=张三丰WuDang
        System.out.printf("id=%2$s，name=%1$s%n", name, id);
    }

    /**
     * %b: 格式化 boolean 类型输出
     */
    @Test
    public void test2() {
        boolean isLogin = false;
        boolean isDeleted = true;
        //isLogin = false; isDeleted = true
        //%b 相当于占位符
        System.out.printf("isLogin = %b; isDeleted = %b%n", isLogin, isDeleted);

        //isDeleted = true
        System.out.printf("isDeleted = %s", isDeleted);
    }

    /**
     * %d 表示将整数格式化为10进制整数
     * %o 表示将整数格式化为8进制整数
     * %x 表示将整数格式化为16进制整数
     * %X 表示将整数格式化为16进制整数，并且字母变成大写形式
     */
    @Test
    public void test3() {
        /*** 输出整数类型***/
        Integer[] integers = new Integer[]{20, 35};

        //20、35, 24、43, 14、23, 14、23
        System.out.printf("%d、%d, ", integers[0], integers[1]);
        System.out.printf("%o、%o, ", integers[0], integers[1]);
        System.out.printf("%x、%x, ", integers[0], integers[1]);
        System.out.printf("%X、%X", integers[0], integers[1]);
    }

    /**
     * %e 表示以科学技术法输出浮点数
     * %E 表示以科学技术法输出浮点数，并且为大写形式
     * %f 表示以十进制格式化输出浮点数
     * %.1f 表示保留小数点1位，%.2f 表示保留小数点2位，以此类推
     */
    @Test
    public void test4() {
        Float salary = 9500.89456f;
        Double money = 999889.89656;

        //9.500895e+03、9.998899e+05，9.500895E+03、9.998899E+05，9500.89、999889.8966
        System.out.printf("%e、%e，", salary, money);
        System.out.printf("%E、%E，", salary, money);
        System.out.printf("%.2f、%.4f%n", salary, money);

        //没有什么是字符串输出不了的：9500.895,999889.89656
        System.out.printf("%s,%s", salary, money);
    }

    /**
     * %t 表示格式化日期/时间类型
     * %T 日期/时间的大写形式
     * <p>
     * %t、%T 之后用特定的字母表示不同的输出格式：
     * y 表示输出日期的年份（2位数的年，如2019的19）
     * Y 表示输出日期的年份（4位数的年，如 2019）
     * m 表示输出日期的月份，如 06
     * d 表示输出日期的日号，如 21
     * b 表示输出月份的中文名称，如 六月
     * H 表示输出时间的时（24进制）
     * I 表示输出时间的时（12进制）
     * M 表示输出时间的分
     * S 表示输出时间的秒
     * L 表示输出时间的毫秒
     * p 表示输出时间的上午或下午信息
     * A 表示星期几的全称
     * a 表示星期几的简称
     * c 表示日期时间的详细信息，如：星期日 六月 21 21:25:41 CST 2020
     * </p>
     *
     * <p>
     * 常见日期组合：
     * D 表示 "%tm/%td/%ty" 的组合
     * F 表示 "%tY-%tm-%td" 的组合（常用）
     * R 表示 "%tH:%tM" 的组合
     * T 表示 "%tH:%tM:%tS" 的组合（常用）
     * r 表示 "%tI:%tM:%tS %Tp" 的组合（常用）
     * </p>
     */
    @Test
    public void test5() {
        Date date = new Date();
        long dateTime = date.getTime();
        /**
         * 1、1$ 表示取后面的第一个参数，2$ 表示取后面的第二个参数，以此类推，放在 %t 的中间。
         * 2、因为格式中有6个 %t 占位符，后面的参数只有两个，所以必须指定 变量编号
         * 3、Date 类型和 long 类型的毫秒值都可以作为日期参数
         * 4、输出：20-06-21，2020/06/21
         */
        System.out.printf("%1$ty-%1$tm-%1$td，%2$tY/%2$tm/%2$td%n", date, dateTime);

        //每一个 %t 都相当于一个占位符
        //输出：2020/六月/21
        System.out.printf("%tY/%tB/%td%n", date, date, date);

        //%n 表示换行
        //输出：06/21/20、2020-06-21
        System.out.printf("%tD、%tF%n", dateTime, date);

        //输出：2020-06-21 21:18:44.571(下午)
        System.out.printf("%1$tF %1$tH:%1$tM:%1$tS.%1$tL(%1$tp)%n", date);

        //常用写法。输出：2020-06-21 21:24:59 星期日
        System.out.printf("%1$tF %1$tT %1$tA%n", date);

        //常用写法：输出：2020-06-21 09:25:22 下午 星期日
        System.out.printf("%1$tF %1$tr %1$ta%n", date);

    }
}
