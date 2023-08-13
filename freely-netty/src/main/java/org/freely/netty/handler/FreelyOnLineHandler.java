package org.freely.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.freely.netty.entity.protocol.impl.request.OnLinePacket;
import org.freely.netty.entity.protocol.impl.response.OnLineResponsePacket;
import org.freely.netty.session.SessionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@ChannelHandler.Sharable
@Component
public class FreelyOnLineHandler extends SimpleChannelInboundHandler<OnLinePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, OnLinePacket onLinePacket) throws Exception {
        Set<String> allKey = SessionUtils.getAllKey();
        OnLineResponsePacket onLineResponsePacket = new OnLineResponsePacket();
        ArrayList<OnLineResponsePacket.OnLineResponseItem> users = new ArrayList<>();
        allKey.forEach((item)->{
            OnLineResponsePacket.OnLineResponseItem build = OnLineResponsePacket.OnLineResponseItem.builder().userName(item).LoginTime(LocalDateTime.now()).build();
            users.add(build);
        });
        onLineResponsePacket.setUser(users);
        channelHandlerContext.channel().writeAndFlush(onLineResponsePacket);
    }
}
