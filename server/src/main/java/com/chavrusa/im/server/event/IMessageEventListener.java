package com.chavrusa.im.server.event;

import com.chavrusa.im.server.communication.CustomLoginResult;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 *
 */
public interface IMessageEventListener {
    boolean ontransbufferC2cCallback();
    boolean onTransBufferCallback();
    CustomLoginResult onUserLoginActionCallback();
}
