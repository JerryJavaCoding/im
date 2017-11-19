package com.chavrusa.im.server.utils;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class ServerToolKits {
    /**
     * @param byteBuf
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseToObj(ByteBuf byteBuf, Class<T> clazz) {
        int length=byteBuf.readableBytes();
        byte[] bytes=new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),bytes);
        String tran=new String(bytes,0,length);
        return JSON.parseObject(tran, clazz);
    }

    /**
     * @param object
     * @return
     */
    public static String parseFromObj(Object object) {
        return JSON.toJSONString(object);
    }

}
