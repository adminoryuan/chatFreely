package org.freely.netty.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.freely.netty.entity.protocol.impl.request.LogoutPacket;
import org.freely.netty.entity.protocol.impl.request.MessagePacket;
import org.freely.netty.entity.protocol.impl.request.OnLinePacket;
import org.freely.netty.enums.PacketType;

import java.util.List;

@Slf4j
public class WebSocketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 注册 JavaTimeModule

        JsonNode dynamicObject = objectMapper.readTree(textWebSocketFrame.text());
        JsonNode body=dynamicObject.get("body");
        int type1 = dynamicObject.get("type").asInt();
        if(PacketType.MessageType.ordinal()==type1)
                list.add(objectMapper.treeToValue(body,MessagePacket.class));
        else if (PacketType.LogoutType.ordinal()==type1)
                list.add(objectMapper.treeToValue(body, LogoutPacket.class));
        else if (PacketType.OnLineType.ordinal()==type1)
            list.add(objectMapper.treeToValue(body, OnLinePacket.class));

    }
}
