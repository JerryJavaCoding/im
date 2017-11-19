package com.chavrusa.im.server.protocal;

import com.chavrusa.im.server.utils.ServerToolKits;
import io.netty.buffer.ByteBuf;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class ProtocalFactory {

    public static Protocal parse(ByteBuf byteBuf) {
        return ServerToolKits.parseToObj(byteBuf, Protocal.class);
    }

}
