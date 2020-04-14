package com.wmx.apachestudy.beanUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 17:26
 */
public class Department {
    private Integer id;
    private String name;
    private List<Person> personList;
    private Map<String, Object> dataMap;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Integer id, String name, List<Person> personList) {
        this.id = id;
        this.name = name;
        this.personList = personList;
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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personList=" + personList +
                ", dataMap=" + dataMap +
                '}';
    }
}
