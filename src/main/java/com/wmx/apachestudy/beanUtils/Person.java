package com.wmx.apachestudy.beanUtils;

import java.time.LocalDateTime;

/**
 * POJO 对象属性建议使用包装类型，因为基本类型会存在默认值的问题，而包装类型默认为 null.
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 14:28
 */
public class Person {
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private Boolean isMarry;
    private Float price;

    public Person() {
    }

    public Person(Integer id, String name, LocalDateTime birthday, boolean isMarry, Float price) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.isMarry = isMarry;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public boolean getMarry() {
        return isMarry;
    }

    public void setMarry(boolean marry) {
        isMarry = marry;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", isMarry=" + isMarry +
                ", price=" + price +
                '}';
    }
}
