package com.wmx.generic.pojo;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/29 19:15
 */
public class Music {
    private Integer id;
    private String name;
    private String author;

    public Music() {
    }

    public Music(Integer id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
