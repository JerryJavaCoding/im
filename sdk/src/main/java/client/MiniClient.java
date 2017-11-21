package client;

import com.chavrusa.im.server.BaseServerLauncher;
import com.chavrusa.im.server.handler.ProtocalDecoder;
import com.chavrusa.im.server.handler.ProtocalEncoder;
import handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Jerry on 2017/11/19 0019.
 */
public class MiniClient {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(BaseServerLauncher.class);
        final ClientHandler clientHandler = new ClientHandler();
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
                            ProtocalDecoder protocalDecoder = new ProtocalDecoder();
                            socketChannel.pipeline().addLast().addLast(protocalDecoder, protocalEncoder, clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host,PORT).sync();
            logger.info(" connect to Server on {}", channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainEventLoopGroup.shutdownGracefully();

        }

    }
}
