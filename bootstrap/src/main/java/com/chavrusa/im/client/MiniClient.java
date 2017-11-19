package com.chavrusa.im.client;

import com.chavrusa.im.server.BaseServerLauncher;
import com.chavrusa.im.server.handler.ProtocalDecoder;
import com.chavrusa.im.server.handler.ProtocalEncoder;
import com.chavrusa.im.server.handler.ServerCoreHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

/**
 * Created by Jerry on 2017/11/19 0019.
 */
public class MiniClient {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(BaseServerLauncher.class);
        final ClientHandler clientHandler = new ClientHandler();
        final ProtocalDecoder protocalDecoder = new ProtocalDecoder();
        final ProtocalEncoder protocalEncoder = new ProtocalEncoder();
        int PORT = 7015;
        String host="127.0.0.1";
        EventLoopGroup mainEventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            // 绑定线程池
            bootstrap.group(mainEventLoopGroup)
                    // 指定使用的channel,TODO 暂时使用tcp
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            logger.info("connected...; Client:{}", socketChannel.remoteAddress());
                            socketChannel.pipeline().addLast().addLast(protocalDecoder, protocalEncoder, clientHandler);
                        }
                    });
            // 服务器异步创建绑定
            ChannelFuture channelFuture = bootstrap.connect(host,PORT);
            logger.info(" connect to Server on {}", channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainEventLoopGroup.shutdownGracefully();

        }

    }
}
