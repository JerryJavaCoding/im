package com.chavrusa.im.server.communication;

import io.netty.channel.Channel;
import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 对话，用户之间通信的小房间
 *
 * @author Jerry
 * @date 2017/11/22 0022
 */
public class Conversation {
    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    private String roomName;
    private List<User> users = new LinkedList<User>();
    private boolean temporary = true;
    //这里的channel只能用户c2s模式
    private Channel channel=null;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Conversation conversation = new Conversation();


        public Builder roomName(String roomName) {
            conversation.setRoomName(roomName);
            return this;
        }

        public Builder temporary(boolean temporary) {
            conversation.setTemporary(temporary);
            return this;
        }

        public Builder addUser(User user) {
            conversation.getUsers().add(user);
            return this;
        }
        public Builder channel(Channel channel) {
            conversation.setChannel(channel);
            return this;
        }
        public Conversation build() {
            return conversation;
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public int getOnLineUserCnt() {
        return users.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }
}
