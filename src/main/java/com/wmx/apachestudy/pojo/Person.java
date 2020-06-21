package com.wmx.apachestudy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/20 10:54
 */
public class Person implements Serializable {
    private Integer id;
    private String name;
    private Date birthday;
    private Float salry;

    public Person() {
    }

    public Person(Integer id, String name, Date birthday, Float salry) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.salry = salry;
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

    public Float getSalry() {
        return salry;
    }

    public void setSalry(Float salry) {
        this.salry = salry;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salry=" + salry +
                '}';
    }
}
