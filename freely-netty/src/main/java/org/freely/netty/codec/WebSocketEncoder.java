package org.freely.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.freely.commom.utls.JsonUtils;
import org.freely.netty.entity.protocol.PacketBody;
import org.freely.netty.entity.protocol.PacketHeader;
import org.freely.netty.entity.protocol.impl.request.LogoutPacket;
import org.freely.netty.entity.protocol.impl.request.MessagePacket;
import org.freely.netty.entity.protocol.impl.response.OnLineResponsePacket;
import org.freely.netty.enums.PacketType;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<PacketBody> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketBody packet, List<Object> list) throws Exception {
        PacketHeader packetHeader = new PacketHeader();
        packetHeader.setBody(packet);
        if (packet instanceof MessagePacket){
            packetHeader.setType(PacketType.MessageType);
        }else if (packet instanceof LogoutPacket){
            packetHeader.setType(PacketType.LogoutType);
        }else if (packet instanceof OnLineResponsePacket){
            packetHeader.setType(PacketType.OnLineType);
        }
        String frameStr= JsonUtils.toJson(packetHeader);
        System.out.println(frameStr);
        TextWebSocketFrame frame = new TextWebSocketFrame(frameStr);
        list.add(frame);
    }
}
