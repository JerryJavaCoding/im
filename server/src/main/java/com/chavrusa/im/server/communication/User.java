package com.chavrusa.im.server.communication;

import java.util.LinkedList;
import java.util.List;

/**
 * 每个注册用户
 *
 * @author Jerry
 * @date 2017/11/22 0022
 */
public class User {
    private Long uid;
    private String nickName;
    /**
     * 用户在线期间，所有登录过的设备都会在这里记录
     */
    private List<Peer> recPeers = new LinkedList<Peer>();
    /**
     * 理论上，用户和设置之间是多对多关系，但是这里通信只按在线用户作为唯一标志
     */
    private List<Peer> onLinePeers = new LinkedList<Peer>();

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        User user = new User();

        public Builder uid(long uid) {
            user.setUid(uid);
            return this;
        }

        public Builder nickName(String nickName) {
            user.setNickName(nickName);
            return this;
        }

        public Builder addPeer(Peer peer) {
            user.getRecPeers().add(peer);
            user.getOnLinePeers().add(peer);
            return this;
        }

        public User build() {
            return user;
        }
    }

    public Long getUid() {
        return uid;
    }

    public void addPeer(Peer peer) {
        getRecPeers().add(peer);
        getOnLinePeers().add(peer);
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Peer> getRecPeers() {
        return recPeers;
    }

    public void setRecPeers(List<Peer> recPeers) {
        this.recPeers = recPeers;
    }

    public List<Peer> getOnLinePeers() {
        return onLinePeers;
    }

    public void setOnLinePeers(List<Peer> onLinePeers) {
        this.onLinePeers = onLinePeers;
    }
}
