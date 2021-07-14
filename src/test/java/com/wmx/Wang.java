package com.wmx;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

        String[] DATE_PARSE_PATTERNS = {"yyyy-MM", "yyyy/MM", "yyyy-MM-dd", "yyyy/MM/dd",
                "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};

        Date parseDate = DateUtils.parseDate("2021/3/3", DATE_PARSE_PATTERNS);
        System.out.println(parseDate);

    }


    @Test
    public void test() {
        List<String> list1 = Lists.newArrayList("1","2","3");
        List<String> list2 = Lists.newArrayList("13","2","3");

        boolean retainAll = list1.removeAll(list2);
        System.out.println(list1);
        System.out.println(list2);


    }


}
