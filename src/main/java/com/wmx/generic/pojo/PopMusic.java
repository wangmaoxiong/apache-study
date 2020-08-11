package com.wmx.generic.pojo;

import java.util.Date;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/29 19:16
 */
public class PopMusic extends Music {
    private Date publishTime;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "PopMusic{" +
                "publishTime=" + publishTime +
                '}';
    }
}
