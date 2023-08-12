package org.freely.netty.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.api.message.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.freely.commom.utls.JsonUtils;
import org.freely.netty.entity.protocol.PacketHeader;
import org.freely.netty.entity.protocol.impl.LogoutPacket;
import org.freely.netty.entity.protocol.impl.MessagePacket;
import org.freely.netty.enums.PacketType;

import java.util.List;

@Slf4j
public class WebSocketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        System.out.println(textWebSocketFrame.text());
        PacketHeader packetHeader= JsonUtils.fromJson(textWebSocketFrame.text(), PacketHeader.class);
        JsonNode bodyNode = packetHeader.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        if (packetHeader.getType().equals(PacketType.MessageType)) {
            MessagePacket messagePacket = objectMapper.treeToValue(bodyNode,MessagePacket.class);
            list.add(messagePacket);
        } else if (packetHeader.getType().equals(PacketType.LogoutType)) {
            LogoutPacket logoutPacket = objectMapper.treeToValue(bodyNode, LogoutPacket.class);
            list.add(logoutPacket);
        }
    }
}
