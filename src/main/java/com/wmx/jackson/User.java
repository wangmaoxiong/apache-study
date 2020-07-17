package com.wmx.jackson;

/**
 * 用户实体
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/16 14:33
 */

import java.util.Date;

public class User {
    private Integer uId;
    private String uName;
    private Date birthday;
    private Float price;

    public User() {
    }

    public User(Integer uId, String uName, Date birthday, Float price) {
        this.uId = uId;
        this.uName = uName;
        this.birthday = birthday;
        this.price = price;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", birthday=" + birthday +
                ", price=" + price +
                '}';
    }
}