package com.chavrusa.im.server.communication;

import org.joda.time.DateTime;

/**
 * 终端，如平板、手机、mac、pc等
 * @author Jerry
 * @date 2017/11/22 0022
 */
public class Peer {
    private Long id;
    private String host;
    private Integer port;
    private DateTime lastLoginTime;

    public static  Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Peer peer=new Peer();

        public Builder id(long id) {
            peer.setId(id);
            return this;
        }

        public Builder host(String nickName) {
            peer.setHost(nickName);
            return this;
        }

        public Builder port(int port) {
            peer.setPort(port);
            return this;
        }
        public Builder lastLoginTime(DateTime lastLoginTime){
            peer.setLastLoginTime(lastLoginTime);
            return this;
        }

        public Peer build() {
            return peer;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public DateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(DateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
