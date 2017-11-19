package com.chavrusa.im.server.handler;


import com.chavrusa.im.server.event.MessageEventListener;
import com.chavrusa.im.server.processor.LogicProcessor;
import com.chavrusa.im.server.protocal.Protocal;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jerry
 * @date 2017/11/12 0012
 * 核心算法处理类
 */
@ChannelHandler.Sharable
public class ServerCoreHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerCoreHandler.class);

    private MessageEventListener messageEventListener = null;
    private LogicProcessor logicProcessor;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocal byteBuf= (Protocal)msg;
        logger.info("server channelRead...; received:" + byteBuf.toString());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("server channelReadComplete..");
        // 第一种方法：写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        //ctx.flush(); // 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
        //ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("server occur exception:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close(); // 关闭发生异常的连接
    }

    public void messageReceived() {
    }

    public void createLogicProcessor() {
    }

    public ServerCoreHandler() {
    }

    public void sessionCreate() {
    }

    public void sessionClosed() {
    }
}
