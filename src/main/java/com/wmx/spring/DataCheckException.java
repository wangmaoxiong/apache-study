package com.wmx.spring;

/**
 * 自定义数据校验异常
 * 继承 java.lang.RuntimeException 之后，则自己也属于运行时异常
 * throw new DataCheckException() 抛出异常的时候，调用者可以不用强制处理
 */
public class DataCheckException extends RuntimeException {
    //java.lang.Throwable 实现了 java.io.Serializable 序列化接口
    // serialVersionUID 用于控制序列化版本，如果将来不需要做序列化，则可以不用加
    private static final long serialVersionUID = 2425120855442714153L;

    //定义属性表示异常编码。通常跨系统调用时，被调用方都会提供一个异常/错误 编码
    private Integer errorCode;

    public DataCheckException() {
    }

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException(String message, Integer errorCode) {
        super("账户校验错误 [" + message + "]");
        this.errorCode = errorCode;
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}