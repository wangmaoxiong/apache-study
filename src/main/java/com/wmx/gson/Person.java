package com.wmx.gson;

import java.util.Date;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/13 20:28
 */
public class Person {
    private Integer id;
    private String name;
    private Date birthday;
    private Boolean marry;

    public Person() {
    }

    public Person(Integer id, String name, Date birthday, Boolean marry) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.marry = marry;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean isMarry() {
        return marry;
    }

    public void setMarry(Boolean marry) {
        this.marry = marry;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", marry=" + marry +
                '}';
    }
}
