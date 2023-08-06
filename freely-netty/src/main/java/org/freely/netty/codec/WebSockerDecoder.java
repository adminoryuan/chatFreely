package org.freely.netty.codec;

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
public class WebSockerDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        PacketHeader packet= JsonUtils.fromJson(textWebSocketFrame.text(), PacketHeader.class);
        if(packet.getType()== PacketType.MessageType){
            if (packet.getBody().getClass()== MessagePacket.class){
                list.add((MessagePacket)packet.getBody());
            }else {
                log.error("消息发送错误！");
            }
        } else if (packet.getType()==PacketType.LogoutType) {
            if (packet.getBody().getClass()== LogoutPacket.class){
                list.add((LogoutPacket)packet.getBody());
            }else {
                log.error("消息发送错误！");
            }
        }

    }
}
