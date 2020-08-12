package com.wmx.genericity;

/**
 * 页码返回结果对象
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/8/12 14:55
 */
public class ReturnData<T> {
    private String code;
    private String message;
    private T data;

    public ReturnData() {
    }

    public ReturnData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
