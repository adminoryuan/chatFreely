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
import org.freely.netty.service.IRouterService;
import org.freely.netty.session.SessionUtils;
import org.freely.netty.utls.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class WebSocketAuthHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private IRouterService iRouterService;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest msg1 = (FullHttpRequest) msg;

            //根据请求头的 auth-token 进行鉴权操作
            String authToken = msg1.headers().get("Authorization");
            if (StringUtils.isEmpty(authToken)) {
                noAuthResponse(ctx);
                return;
            }
            TokenDto tokenDto = new TokenDto();
            if ((tokenDto= RedisUtils.get(authToken,TokenDto.class))==null) {
                noAuthResponse(ctx);
            }
            tokenDto.setUserId(1L);
            tokenDto.setUserName(tokenDto.getUserName());
            //iRouterService.addRouter(tokenDto, ctx.channel());
            SessionUtils.bindSession(tokenDto.getUserName(), ctx.channel());
           // ctx.pipeline().remove(this);
        }
        ctx.fireChannelRead(msg);
    }

    private void noAuthResponse(ChannelHandlerContext ctx) {
        DefaultFullHttpResponse response= new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
        ctx.channel().writeAndFlush(response);
        ctx.channel().close();
    }
}
