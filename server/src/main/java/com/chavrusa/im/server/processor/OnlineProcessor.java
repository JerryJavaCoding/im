package com.chavrusa.im.server.processor;

import com.chavrusa.im.server.communication.Conversation;
import com.chavrusa.im.server.communication.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Jerry
 * @date 2017/11/20 0020
 */
public class OnlineProcessor {
    private static Map<String, Conversation> conversatopmMap = new HashMap<String, Conversation>();
    private static Map<Long, User> onlineUser = new HashMap<Long, User>();

    public static void addConversation(Conversation conversation) {
        conversatopmMap.put(conversation.getId(), conversation);
    }

    public static void addOnlineUser(User user) {
        if (!onlineUser.keySet().contains(user.getUid())) {
            onlineUser.put(user.getUid(), user);
        }
    }
    public static long getOnlineUserCnt(){
        return onlineUser.size();
    }

    /**
     *  TODO 暂时先按索引选择对话，后续改进
     * @param select
     * @return
     */
    public static Conversation getSelectedConversation(int select){
        Iterator<String> iterator = conversatopmMap.keySet().iterator();
        if(select>=conversatopmMap.size()){
            return null;
        }
        int index=0;
        String selectedCid=null;
        while (iterator.hasNext()){
            String cid = iterator.next();
            if(index==select){
                selectedCid=cid;
            }
            index++;
        }
        return conversatopmMap.get(selectedCid);
    }
}
