package com.chavrusa.im.server.processor;

import com.chavrusa.im.server.handler.ServerCoreHandler;
import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.protocal.ProtocalFactory;
import com.chavrusa.im.server.utils.LogKits;
import com.chavrusa.im.server.win.TransportWind;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class LogicProcessor {



    public LogicProcessor() {

    }

    public void processC2CMessage(Protocal protocal) {
    }

    public void processorC2SMessage(ChannelHandlerContext ctx,Protocal protocal) {
        LogKits.WIN.info("收到客户端{}:{}",protocal.getFromHost(),protocal.getDataContent());
        TransportWind.sendToServer(ctx);
    }

    public void processAck(Protocal protocal) {
    }

    public void processorLogin(ChannelHandlerContext ctx, Protocal protocal) {
        LogKits.WIN.info("{} 登录成功", protocal.getFromHost());
        Protocal backProtocal = ProtocalFactory.createLoginAck(protocal.getFromHost());
        ctx.writeAndFlush(backProtocal);
    }

    public void processKeepAlive(ChannelHandlerContext ctx, Protocal protocal) {
    }

}
