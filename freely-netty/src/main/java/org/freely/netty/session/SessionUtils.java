package org.freely.netty.session;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtils {
    private static final Map<String,Channel> channelSessionMap = new ConcurrentHashMap<>();

    public static Set<String> getAllKey(){
        return channelSessionMap.keySet();
    }
    public static void bindSession(String sessionId,Channel channel) {
        channelSessionMap.put(sessionId,channel);
    }

    public static void unbindSession(String sessionId) {
        channelSessionMap.remove(sessionId);
    }

    public static Channel getSessionId(String sessionId) {
        return channelSessionMap.get(sessionId);
    }
}
