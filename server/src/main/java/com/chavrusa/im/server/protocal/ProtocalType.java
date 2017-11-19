package com.chavrusa.im.server.protocal;

/**
 * 请求协议的类型，服务端根据这个进行不同的逻辑处理
 *
 * @author Jerry
 * @date 2017/11/12 0012
 */
public enum ProtocalType {
    /**
     *
     */
    LOGIN(0),
    /**
     *
     */
    KEEP_ALIVE(1),
    /**
     *
     */
    COMMUNICATE(2),
    /**
     *
     */
    UN_LOGIN(3),
    /**
     *
     */
    ACK(4),;
    private int value;

    ProtocalType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
