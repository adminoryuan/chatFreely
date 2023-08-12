package org.freely.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.freely.commom.entity.dto.TokenDto;
import org.freely.netty.entity.protocol.impl.LogoutPacket;
import org.freely.netty.session.SessionUtils;
import org.freely.netty.utls.RedisUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketLogoutHandler extends SimpleChannelInboundHandler<LogoutPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutPacket logoutPacket) throws Exception {
        String token = logoutPacket.getToken();
        TokenDto tokenDto=null;
        if ((tokenDto= RedisUtils.get(token,TokenDto.class))==null) {
            RedisUtils.removeKey(token);
            SessionUtils.unbindSession(tokenDto.getUserName());
        }
        ctx.close().sync();
    }
}
