package org.freely.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.freely.netty.entity.protocol.impl.LogoutPacket;
import org.freely.netty.entity.protocol.impl.MessagePacket;
import org.freely.netty.enums.MessageType;
import org.freely.netty.session.SessionUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messagePacket) throws Exception {
        String sender = messagePacket.getSender();
        ctx.executor().execute(new Runnable() {
            @Override
            public void run() {
                SessionUtils.getSessionId(sender).writeAndFlush(messagePacket);
            }
        });
    }
}
