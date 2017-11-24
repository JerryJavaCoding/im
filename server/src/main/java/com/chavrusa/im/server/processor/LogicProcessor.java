package com.chavrusa.im.server.processor;


import com.chavrusa.im.server.communication.Conversation;
import com.chavrusa.im.server.communication.CustomLoginResult;
import com.chavrusa.im.server.communication.Peer;
import com.chavrusa.im.server.communication.User;
import com.chavrusa.im.server.event.IMessageEventListener;
import com.chavrusa.im.server.protocal.Protocal;
import com.chavrusa.im.server.protocal.ProtocalFactory;
import com.chavrusa.im.server.utils.LogKits;
import com.chavrusa.im.server.win.TransportWind;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.joda.time.DateTime;


/**
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class LogicProcessor {
    private static Peer service = Peer.builder().host("127.0.0.1").port(1235).id(11111111).lastLoginTime(new DateTime()).build();
    private static User serviceUser = User.builder().nickName("服务器").uid(000000000000).addPeer(service).build();
    public LogicProcessor() {

    }

    public void processC2CMessage(Protocal protocal) {
    }

    public void processorC2SMessage(ChannelHandlerContext ctx, Protocal protocal, IMessageEventListener messageEventListener) {
        LogKits.WIN.info("收到客户端{}:{}", protocal.getFromHost(), protocal.getDataContent());
        TransportWind.switchConversation();
//        TransportWind.sendToServer(ctx);
    }

    public void processAck(Protocal protocal) {
    }

    public void processorLogin(ChannelHandlerContext ctx, Protocal protocal, IMessageEventListener messageEventListener) {
        //暴露给用户自定义的登录接口
        CustomLoginResult customLoginResult = messageEventListener.onUserLoginActionCallback();
        if (!customLoginResult.isLoginSuccess()) {
            LogKits.WIN.info("{} 登录失败", protocal.getFromHost());
            Protocal backProtocal = ProtocalFactory.createLoginAck(protocal.getFromHost(),"loginFail");
            ctx.writeAndFlush(backProtocal);
        }
        User user = customLoginResult.getUser();
        Conversation conversation = Conversation.builder().temporary(true).addUser(user).addUser(serviceUser).roomName("c2s").channel(ctx.channel()).build();
        OnlineProcessor.addConversation(conversation);
        OnlineProcessor.addOnlineUser(user);
        LogKits.WIN.info("{} 登录成功,当前在线用户数虎：{}", protocal.getFromHost(),OnlineProcessor.getOnlineUserCnt());

        Protocal backProtocal = ProtocalFactory.createLoginAck(protocal.getFromHost(),"loginSuccess");
        ctx.writeAndFlush(backProtocal);
    }

    public void processKeepAlive(ChannelHandlerContext ctx, Protocal protocal, IMessageEventListener messageEventListener) {
    }

}
