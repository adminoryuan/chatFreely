package org.freely.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.freely.commom.utls.JsonUtils;
import org.freely.netty.entity.protocol.PacketHeader;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<PacketHeader> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketHeader packet, List<Object> list) throws Exception {
        String frameStr= JsonUtils.toJson(packet);
        TextWebSocketFrame frame = new TextWebSocketFrame(frameStr);
        list.add(frame);
    }
}
