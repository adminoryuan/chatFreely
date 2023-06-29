package org.freely.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.commons.lang3.StringUtils;
import org.freely.commom.entity.dto.TokenDto;
import org.freely.netty.session.SessionUtils;
import org.freely.netty.utls.RedisUtils;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class WebSocketAuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest msg1 = (FullHttpRequest) msg;
            //根据请求头的 auth-token 进行鉴权操作
            String authToken = msg1.headers().get("auth-token");

            if (StringUtils.isEmpty(authToken)) {
                noAuthResponse(ctx);
                return;
            }
            TokenDto tokenDto;
            if ((tokenDto= RedisUtils.get(authToken,TokenDto.class))==null) {
                noAuthResponse(ctx);
            }
            SessionUtils.bindSession(tokenDto.getUserName(),ctx.channel());
        }
        ctx.fireChannelRead(msg);
    }
    private void noAuthResponse(ChannelHandlerContext ctx) {
        ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED));
        ctx.channel().close();
    }
}
