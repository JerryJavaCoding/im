package com.chavrusa.im.server.win;

import com.chavrusa.im.server.communication.Conversation;
import com.chavrusa.im.server.processor.OnlineProcessor;
import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.protocal.ProtocalFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 通信窗口
 *
 * @author Jerry
 * @date 2017/11/20 0020
 */
public class TransportWind {

    private static Logger logger = LoggerFactory.getLogger(TransportWind.class);

    public static void sendToServer(ChannelHandlerContext ctx) {
        logger.info("请输入：");
        String strFromConsole =  new Scanner(System.in).next();
        Protocal p = ProtocalFactory.builder().toHost("127.0.0.1").fromHost("127.0.0.1").type(2).dataContent(strFromConsole).build();
        ctx.writeAndFlush(p);
    }

    public static void switchConversation() {
     Scanner in = new Scanner(System.in);

        logger.info("请输入要进行通信的客户端：");
        int switchConversation = 0;
        try {
            switchConversation =in.nextInt();

            Conversation selectedConversation = OnlineProcessor.getSelectedConversation(switchConversation);
            if (selectedConversation == null) {
                logger.error("没有该对话。。。。。。");
            }
            Channel channel = selectedConversation.getChannel();
            logger.info("已选中对话：{}，请输入内容：", channel);

            Protocal p = ProtocalFactory.builder().toHost("127.0.0.1").fromHost("127.0.0.1").type(2).dataContent(in.next()).build();
            channel.writeAndFlush(p);
        } catch (Exception e) {
            logger.error("scanner异常");
        }
    }

//    public static Scanner getScannerInstance() {
//        return in;
//    }

}
