package com.wmx.guava.pojo;

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
    private Float salary;

    public Person() {
    }

    public Person(Integer id, String name, Date birthday, Float salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
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

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}
