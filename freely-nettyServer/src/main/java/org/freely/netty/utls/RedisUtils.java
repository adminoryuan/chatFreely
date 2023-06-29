package org.freely.netty.utls;

import redis.clients.jedis.Jedis;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RedisUtils {
    private static final String HOST = "";
    private static final int PORT = 0;
    private static final Jedis jedis = new Jedis(HOST, PORT);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 设置键值对
    public static <T> void set(String key, T value) throws IOException {
        String json = objectMapper.writeValueAsString(value);
        jedis.set(key, json);
    }

    // 获取键对应的值
    public static <T> T get(String key, Class<T> clazz) throws IOException {
        String json = jedis.get(key);
        return objectMapper.readValue(json, clazz);
    }

    // 关闭Redis连接
    public static void close() {
        jedis.close();
    }
}

