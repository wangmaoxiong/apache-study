package com.wmx;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public void test3() throws ParseException {

        String[] DATE_PARSE_PATTERNS = { "yyyy-MM", "yyyy/MM", "yyyy-MM-dd", "yyyy/MM/dd",
                "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };

        Date parseDate = DateUtils.parseDate("2021/3/3", DATE_PARSE_PATTERNS);
        System.out.println(parseDate);

    }


    @Test
    public void test() {
        String[] arr = {"ds", "ffg", "787"};
        ArrayList<String> arrayList = Lists.newArrayList(arr);
        System.out.println(arrayList);

        arrayList.add("00099");
        System.out.println(arrayList);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", "1001");
        dataMap.put("CODE", "001002");

        System.out.println(dataMap.get("ID"));
        System.out.println(dataMap.get("code"));

        System.out.println(StringUtils.equals(dataMap.get("xxx") + "", "1"));

        StopWatch stopwatch = StopWatch.createStarted();

    }


}
