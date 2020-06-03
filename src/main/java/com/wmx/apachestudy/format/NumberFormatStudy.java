package com.wmx.apachestudy.format;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/6/2 19:50
 */
public class NumberFormatStudy {
    /**
     * getInstance(Locale inLocale)：返回指定区域设置的通用数字格式
     * getInstance() : 返回当前默认值的通用数字格式
     * java.util.Locale#getDefault()：获取此 Java 虚拟机实例的默认区域设置的当前值。
     * java.text.NumberFormat#setGroupingUsed(boolean newValue): 设置整数部分是否使用 "," 3个为一组进行分组，默认为 true。
     */
    public void test1() {
        double salaryYear = 350640.82988;
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        String format = numberFormat.format(salaryYear);
        //待格式化值：350640.82988
        System.out.println("待格式化值：" + salaryYear);
        //默认格式化：350,640.83
        System.out.println("默认格式化：" + format);

        numberFormat.setGroupingUsed(false);
        //输出：350640.83
        System.out.println(numberFormat.format(salaryYear));
    }

    /**
     * java.text.NumberFormat#getInstance() 返回当前默认值的通用数字格式
     */
    public void test2() {
        try {
            double salaryYear = 350640.82988;
            NumberFormat numberFormat = NumberFormat.getInstance();
            //强制转换成 DecimalFormat
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;

            //待格式化值：350640.82988
            System.out.println("待格式化值：" + salaryYear);
            //默认格式化：350,640.83
            System.out.println("默认格式化：" + decimalFormat.format(salaryYear));

            /**
             * applyPattern(String pattern)：将给定的模式应用于此格式对象，使用：, # 0 . 几个符号
             * setRoundingMode(RoundingMode roundingMode): 设置舍入模式，RoundingMode 枚举可选值有：
             *      UP：向上舍入，如 5.5 -> 6，1.1 -> 2，1.0 -> 1，-1.6 -> -2，-1.1 -> -2，-1.0 -> -1
             *      DOWN：向下舍入、5.5 -> 5，1.1 -> 1，1.0 -> 1，-1.6 -> -1，-1.1 -> -1，-1.0 -> -1
             *      CEILING: 如果是正数则使用 UP 向上舍入，如果是负数则使用 DOWN 向下舍入
             *      FLOOR：如果是正数则使用 UP 向下舍入，如果是负数则使用 DOWN 向上舍入
             *      HALF_UP：四舍五入模式（关键在5），如 5.5->6，1.6->2，1.1->1，1.0->1，-1.0->1，-1.1->-1，-1.6->-2，-5.5->-6
             *      HALF_DOWN: 五舍四入模式（关键在5），如 5.5->5，1.6->2，1.1->1，1.0->1，-1.0->1，-1.1->-1，-1.6->-2，-5.5->-5
             *      HALF_EVEN: 如果整数部分是奇数，则使用 HALF_UP；如果整数部分是偶数，则使用 HALF_DOWN。
             *      默认为 HALF_EVEN
             */
            //小数部分保留4位，不足的补零。整数部分每隔3位用 "," 号隔开，及千分位
            decimalFormat.applyPattern("#,###.0000");
            //保留 4 位小数：350,640.8299
            System.out.println("保留 4 位小数：" + decimalFormat.format(salaryYear));
            //设置舍入模式为 HALF_UP（四舍五入模式），否则默认的是 HALF_EVEN
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            System.out.println("四舍五入模式：" + decimalFormat.format(salaryYear));
            /**
             * setPositivePrefix (String newValue): 为正数加上前缀，如 +123, $123, sFr123。对正数有效，负数无效。
             * setPositiveSuffix (String newValue): 为正数加上后缀，如 123%。对正数有效，负数无效。
             */
            decimalFormat.setPositivePrefix("￥");
            System.out.println("正数加上前缀：" + decimalFormat.format(salaryYear));

            decimalFormat.setPositivePrefix("");
            decimalFormat.setPositiveSuffix("‱");
            System.out.println("正数加上后缀：" + decimalFormat.format(salaryYear));

            decimalFormat.setPositivePrefix("");
            decimalFormat.setPositiveSuffix("");

            /**
             * setMaximumIntegerDigits(int newValue): 设置数字整数部分中允许的最大位数，超过时，会被舍弃，默认为 40
             * setMinimumIntegerDigits(int newValue): 设置数字整数部分中允许的最小位数，不足时，首部用0补齐，默认为 1
             */
            decimalFormat.setMaximumIntegerDigits(5);
            System.out.println("限制整数最大位数为5：" + decimalFormat.format(salaryYear));

            decimalFormat.setMinimumIntegerDigits(9);
            System.out.println("限制整数最小位数为9：" + decimalFormat.format(salaryYear));

            decimalFormat.setMaximumIntegerDigits(40);
            decimalFormat.setMinimumIntegerDigits(1);
            /**
             * setMaximumFractionDigits(int newValue)：设置数字的小数部分所允许的最大位数，超过的会被截断。默认为3
             * setMinimumFractionDigits(int newValue): 设置数字的小数部分中允许的最小位数，不足的，末尾用0补齐.默认为0
             */
            decimalFormat.setMaximumFractionDigits(3);
            //限制小数最大位数为3：350,640.830
            System.out.println("限制小数最大位数为3：" + decimalFormat.format(salaryYear));

            decimalFormat.setMinimumFractionDigits(6);
            //限制小数最小位数为6：350,640.829880
            System.out.println("限制小数最小位数为6：" + decimalFormat.format(salaryYear));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test3() {
        try {
            double salaryYear = 350640.82988;
            //待格式化值：350640.82988
            System.out.println("待格式化值：" + salaryYear);
            /**
             *  getPercentInstance()：返回当前默认值的百分比格式(建议)
             *  getPercentInstance(Locale inLocale): 返回指定区域设置的百分比格式。
             */
            NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.CHINA);
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            //默认格式化：35,064,083%
            System.out.println("默认格式化：" + decimalFormat.format(salaryYear));
            decimalFormat.setMinimumFractionDigits(2);
            //小数点保留2位：35,064,082.99%
            System.out.println("小数点保留2位：" + decimalFormat.format(salaryYear));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test4() {
        try {
            double salaryYear = 350640.82988;
            //待格式化值：350640.82988
            System.out.println("待格式化值：" + salaryYear);

            /**
             * getCurrencyInstance() ：返回当前默认值的货币格式，中国是 ￥，美国是 $
             */
            NumberFormat curFormat = NumberFormat.getCurrencyInstance();
            DecimalFormat decimalFormat = (DecimalFormat) curFormat;
            //默认格式化：￥350,640.83
            System.out.println("默认格式化：" + decimalFormat.format(salaryYear));

            decimalFormat.setMinimumFractionDigits(4);
            System.out.println("保留4位小数：" + decimalFormat.format(salaryYear));

            //setMultiplier (int newValue)：乘法操作
            decimalFormat.setMultiplier(100);
            //设置后缀
            decimalFormat.setPositiveSuffix("%");
            System.out.println("乘以100加后缀：" + decimalFormat.format(salaryYear));

            //使用美元货币符号
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
            //$350,640.83
            System.out.println(numberFormat.format(salaryYear));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test5() {
        double salaryYear = 350640.82988;
        double salaryDay = 350.54;

        //源数据：350640.82988	,	350.54
        System.out.println("源数据：" + salaryYear + "\t,\t" + salaryDay);
        DecimalFormat df = new DecimalFormat("0000.000");
        //格式1：350640.830	,	0350.540
        System.out.println("格式1：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
        df.applyPattern("#");
        //格式2：350641	,	351
        System.out.println("格式2：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
        df.applyPattern(".####");
        //格式3.1：350640.8299	,	350.54
        System.out.println("格式3.1：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
        df.applyPattern(".0000");
        //格式3.2：350640.8299	,	350.5400
        System.out.println("格式3.2：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
        df.applyPattern("00,000,000.00000");
        //格式4：00,350,640.82988	,	00,000,350.54000
        System.out.println("格式4：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
        df.applyPattern("#,###.0000");
        //格式5：350,640.8299	,	350.5400
        System.out.println("格式5(常用格式)：" + df.format(salaryYear) + "\t,\t" + df.format(salaryDay));
    }

    public static void main(String[] args) {
        NumberFormatStudy numberFormatStudy = new NumberFormatStudy();
//        numberFormatStudy.test1();
//        numberFormatStudy.test2();
//        numberFormatStudy.test3();
//        numberFormatStudy.test4();
        numberFormatStudy.test5();
    }
}
