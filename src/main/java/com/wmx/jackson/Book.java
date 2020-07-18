package com.wmx.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 1、@JsonFormat：指定日期属性序列化与反序列化时的格式，timezone = "GMT+8" 设置时区，表示 +8 小时，否则会少8小时
 * 2、@JsonFormat 添加到需要指定格式的日期属性上
 * 3、JsonInclude(JsonInclude.Include.NON_NULL)：对值为 null 的属性不进行序列化
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/17 8:34
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private Integer id;
    private String title;
    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publish;

    public Book() {
    }

    public Book(Integer id, String title, Float price, Date publish) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publish = publish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publish=" + publish +
                '}';
    }
}
