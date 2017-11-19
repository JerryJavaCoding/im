package com.chavrusa.im.server.protocal;

/**
 * @author Jerry
 * @date 2017/11/12 0012
 */
public class Protocal {
    private Integer type;
    private String fromHost;
    private String toHost;
    private String dataContent;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFromHost() {
        return fromHost;
    }

    public void setFromHost(String fromHost) {
        this.fromHost = fromHost;
    }

    public String getToHost() {
        return toHost;
    }

    public void setToHost(String toHost) {
        this.toHost = toHost;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[from:").append(fromHost).append(",to:").append(toHost).append(",type:").append(type).append(",dataContent:").append(dataContent);
        return stringBuffer.toString();
    }
}
