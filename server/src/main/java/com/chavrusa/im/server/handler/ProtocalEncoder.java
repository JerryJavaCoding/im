package com.chavrusa.im.server.handler;

import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.utils.ServerToolKits;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Jerry
 * @date 2017/11/19 0019
 */
@ChannelHandler.Sharable
public class ProtocalEncoder extends MessageToByteEncoder<Protocal> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Protocal protocal, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(ServerToolKits.parseFromObj(protocal).getBytes());
    }
}
