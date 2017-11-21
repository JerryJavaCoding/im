package handler;

import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.win.TransportWind;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jerry on 2017/11/19 0019.
 */
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private static String SERVER_HOST = "127.0.0.1";

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocal result = (Protocal) msg;
       logger.info("收到服务端请求消息：[{}]",result.getDataContent());
        TransportWind.sendToServer(ctx);

    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Protocal protocal= new ProtocalFactory.Builder().fromHost(InetAddress.getLocalHost().getHostAddress().toString()).toHost(SERVER_HOST).type(ProtocalType.LOGIN.getValue()).build();
//        ctx.writeAndFlush(protocal);
        TransportWind.sendToServer(ctx);

    }
}

