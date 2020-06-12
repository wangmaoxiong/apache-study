package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/12 15:34
 */
public class StringUtilsTest {

    @Test
    public void test1(){
        StringUtils.defaultIfBlank("","");
    }

}
