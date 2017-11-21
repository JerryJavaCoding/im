package com.chavrusa.im.server.protocal;

import com.chavrusa.im.server.utils.ServerToolKits;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class ProtocalFactory {
    private static Logger logger = LoggerFactory.getLogger(ProtocalFactory.class);

    public ProtocalFactory() {
    }

    public static Protocal parse(ByteBuf byteBuf) {
        return ServerToolKits.parseToObj(byteBuf, Protocal.class);
    }

    public static Protocal createLoginAck(String toHost) {
        Protocal protocal = new Protocal();
        try {
            protocal.setFromHost(InetAddress.getLocalHost().getHostAddress().toString());
        } catch (UnknownHostException e) {
            logger.error("获取本地网络地址失败，error：{}", e);
            protocal.setFromHost("server address");
        }
        protocal.setToHost(toHost);
        protocal.setType(ProtocalType.ACK.getValue());
        protocal.setDataContent("login success");
        return protocal;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        Protocal protocal = new Protocal();

        public  Builder toHost(String toHost) {
            protocal.setToHost(toHost);
            return this;
        }

        public Builder fromHost(String fromHost) {
            protocal.setFromHost(fromHost);
            return this;
        }

        public Builder type(int type) {
            protocal.setType(type);
            return this;
        }

        public Builder dataContent(String content) {
            protocal.setDataContent(content);
            return this;
        }

        public Protocal build() {
            return protocal;
        }
    }

}
