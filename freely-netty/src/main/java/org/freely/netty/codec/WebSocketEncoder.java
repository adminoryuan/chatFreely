package org.freely.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.freely.commom.utls.JsonUtils;
import org.freely.netty.entity.protocol.PacketHeader;
import org.freely.netty.entity.protocol.impl.MessagePacket;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<MessagePacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessagePacket packet, List<Object> list) throws Exception {
        String frameStr= JsonUtils.toJson(packet);
        TextWebSocketFrame frame = new TextWebSocketFrame(frameStr);
        list.add(frame);
    }
}
