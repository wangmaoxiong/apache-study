package com.wmx.apachestudy.beanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/15 10:50
 */
public class User {
    private Integer id;
    private String name;
    private String[] phones;
    private List<String> address;

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
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

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", address=" + address +
                '}';
    }
}
