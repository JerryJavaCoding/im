package com.chavrusa.im.bootstrap.launch;

import com.chavrusa.im.server.communication.CustomLoginResult;
import com.chavrusa.im.server.communication.User;
import com.chavrusa.im.server.event.IMessageEventListener;

import java.util.Random;


/**
 * @author Jerry
 * @date 2017/11/22 0022
 */
public class MessageEventListener implements IMessageEventListener {
    @Override
    public boolean ontransbufferC2cCallback() {
        return false;
    }

    @Override
    public boolean onTransBufferCallback() {
        return false;
    }

    @Override
    public CustomLoginResult onUserLoginActionCallback() {
        CustomLoginResult result = new CustomLoginResult();
        result.setLoginSuccess(true);
        User user = new User();
        user.setNickName("jerry");
        user.setUid(new Random().nextLong());
        result.setUser(user);
        return result;
    }
}
