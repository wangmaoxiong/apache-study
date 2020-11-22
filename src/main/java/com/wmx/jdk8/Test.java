package com.wmx.jdk8;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/21 15:34
 */
public class Test {
    public static void main(String[] args) {

        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append("Gbm_Bs_Ageninfo").append(" a").append(" where a.agency_id = ").append(20803).append(" and a.top_org_id = ").append(430000);
        System.out.println(sql);

        System.out.println("AE2AE001C4BC4C259CBC8CFF443E5801".length());
    }
}
