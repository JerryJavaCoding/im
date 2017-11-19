package com.chavrusa.im.server.processor;

import com.chavrusa.im.server.handler.ServerCoreHandler;
/**
 *
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class LogicProcessor {
    private ServerCoreHandler serverCoreHandler=null;
    public LogicProcessor(ServerCoreHandler serverCoreHandler) {
        this.serverCoreHandler=serverCoreHandler;
    }
    public void processC2CMessage(){}
    public void processorC2SMessage(){}
    public void processAck(){}
    public void processorLogin(){}
    public void processKeepAlive(){}

}
