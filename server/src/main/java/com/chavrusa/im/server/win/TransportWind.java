package com.chavrusa.im.server.win;

import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.protocal.ProtocalFactory;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * 通信窗口
 *
 * @author Jerry
 * @date 2017/11/20 0020
 */
public class TransportWind {
    private static Scanner in = new Scanner(System.in);
    private static Logger logger = LoggerFactory.getLogger(TransportWind.class);

    public static void sendToServer(ChannelHandlerContext ctx) {
        logger.info("请输入：");
        String strFromConsole = TransportWind.getScannerInstance().next();
        Protocal p = ProtocalFactory.builder().toHost("127.0.0.1").fromHost("127.0.0.1").type(2).dataContent(strFromConsole).build();
        ctx.writeAndFlush(p);
    }

    public static Scanner getScannerInstance() {
        return in;
    }

}
