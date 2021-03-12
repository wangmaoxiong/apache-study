package com.wmx;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/12/17 11:20
 */
public class Wang {

    @Test
    public void test1() throws ParseException {
        String nowTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(nowTime);

        System.out.println(LocalDate.now());
        System.out.println(DateUtils.parseDate("2299-12-12", "yyyy-MM-dd"));
    }

    @Test
    public void test2() throws ParseException {
        Date date1 = DateUtils.parseDate("2030-02-12 12:12:14", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtils.parseDate("2020-02-12 12:12:14", "yyyy-MM-dd HH:mm:ss");

        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        localDate1 = localDate1.plusYears(10);

        System.out.println(localDate1);
        System.out.println(localDate2);


        int i = localDate1.compareTo(localDate2);
        System.out.println(i);

        System.out.println(date1.compareTo(date2));
    }

    @Test
    public void test3(){
        System.out.println(NumberUtils.isDigits("001005"));
        Map<String,Object> data = new HashMap<>();
        System.out.println(data.get("id"));
        System.out.println((String) data.get("id"));
        if (StringUtils.equalsIgnoreCase("01",(String)data.get("id"))){
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }


    @Test
    public void test6(){
        System.out.println("12410000777999327p".length());
    }
}
