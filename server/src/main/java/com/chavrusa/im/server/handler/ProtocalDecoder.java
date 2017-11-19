package com.chavrusa.im.server.handler;

import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.protocal.ProtocalFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;


import java.util.List;

/**
 *
 * @author Jerry
 * @date 2017/11/18 0018
 */
public class ProtocalDecoder extends ReplayingDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Protocal protocalObj = ProtocalFactory.parse(byteBuf);
        list.add(protocalObj);
    }
}
