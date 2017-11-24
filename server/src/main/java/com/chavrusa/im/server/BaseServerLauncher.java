package com.chavrusa.im.server;

import com.chavrusa.im.server.event.IMessageEventListener;
import com.chavrusa.im.server.handler.ProtocalDecoder;
import com.chavrusa.im.server.handler.ProtocalEncoder;
import com.chavrusa.im.server.handler.ServerCoreHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 * 框架启动抽象类
 */
public abstract class BaseServerLauncher {
    private static Logger logger = LoggerFactory.getLogger(BaseServerLauncher.class);
    private ServerCoreHandler serverCoreHandler = new ServerCoreHandler();
    private ProtocalEncoder protocalEncoder = new ProtocalEncoder();
    public static int PORT = 7015;
    public static String appkey = null;
    public static boolean debug = true;

    public void startup() throws Exception {
        EventLoopGroup mainEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 绑定线程池
            serverBootstrap.group(mainEventLoopGroup, workerEventLoopGroup)
                    // 指定使用的channel,TODO 暂时使用tcp
                    .channel(NioServerSocketChannel.class)
                    .localAddress(PORT)
                    // 绑定客户端连接时候触发操作
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            logger.info("connected...; Client:{}", socketChannel.remoteAddress());
                            ProtocalDecoder protocalDecoder = new ProtocalDecoder();
                            socketChannel.pipeline().addLast().addLast(protocalDecoder, protocalEncoder, serverCoreHandler);
                        }
                    });
            // 服务器异步创建绑定
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            logger.info(" serverLauncher started and listen on {}", channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } finally {
            mainEventLoopGroup.shutdownGracefully().sync();
            workerEventLoopGroup.shutdownGracefully().sync();
        }
    }

    public void shutdown() {
    }

    public abstract void initListener();
    protected void addListener(IMessageEventListener messageEventListener){
        serverCoreHandler.setMessageEventListener(messageEventListener);
    }
}
