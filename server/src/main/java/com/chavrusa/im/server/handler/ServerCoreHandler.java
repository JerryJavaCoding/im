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
 * @author Jerry
 * @date 2017/11/12 0012
 * 核心算法处理类
 */
@ChannelHandler.Sharable
public class ServerCoreHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerCoreHandler.class);

    private MessageEventListener messageEventListener = null;
    private LogicProcessor logicProcessor=new LogicProcessor();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocal protocal = (Protocal) msg;
        if (protocal == null) {
            logger.error("请求类型不明确,[{}]",protocal.toString());
            return;
        }
        logger.info("server channelRead...; received:" + protocal.toString());
        switch (protocal.getType()) {
            case 0:
                logger.info("登录请求");
                logicProcessor.processorLogin(ctx,protocal);
                break;
            case 1:
                logicProcessor.processKeepAlive(ctx,protocal);
                logger.info("心跳包请求");
                break;
            case 2:
                logger.info("通信请求");
                logicProcessor.processorC2SMessage(ctx,protocal);
                break;
            case 3:break;
            case 4:break;
            default:
                logger.error("请求类型未定义,[{}]",protocal.toString());
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("server channelReadComplete..");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("server occur exception:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close(); // 关闭发生异常的连接
    }

    public void createLogicProcessor() {
    }

    public ServerCoreHandler() {
    }

    public void sessionCreate() {
    }

    public void sessionClosed() {
    }

    public void setLogicProcessor(LogicProcessor logicProcessor) {
        this.logicProcessor = logicProcessor;
    }

    public MessageEventListener getMessageEventListener() {
        return messageEventListener;
    }

    public void setMessageEventListener(MessageEventListener messageEventListener) {
        this.messageEventListener = messageEventListener;
    }
}
