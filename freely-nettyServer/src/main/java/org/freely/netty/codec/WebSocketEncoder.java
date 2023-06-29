package org.freely.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.freely.commom.entity.TextMessagePacket;
import org.freely.commom.utls.JsonUtils;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<TextMessagePacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TextMessagePacket textMessagePacket, List<Object> out) throws Exception {
        String frameStr= JsonUtils.toJson(textMessagePacket);
        TextWebSocketFrame frame = new TextWebSocketFrame(frameStr);
        out.add(frame);
    }
}
