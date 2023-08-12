package org.freely.netty;

import io.netty.channel.*;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import org.freely.netty.codec.WebSocketDecoder;
import org.freely.netty.codec.WebSocketEncoder;
import org.freely.netty.handler.WebSocketAuthHandler;
import org.freely.netty.handler.WebSocketHeartbeatHandler;
import org.freely.netty.handler.WebSocketLogoutHandler;
import org.freely.netty.handler.WebsocketMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

public class ServerInitializer extends ChannelInitializer<Channel> {
    @Autowired
    private WebSocketAuthHandler webSocketAuthHandler;
    @Autowired
    private WebSocketLogoutHandler logoutHandler;

    @Autowired
    private WebsocketMessageHandler messageHandler;

    @Value("${websocket.router}")
    private  String WebSocketRouter;
    @Autowired
    private WebSocketHeartbeatHandler webSocketHeartbeatHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(webSocketAuthHandler);
        pipeline.addLast(new WebSocketServerProtocolHandler(WebSocketRouter));
        pipeline.addLast(new WebSocketEncoder());
        pipeline.addLast(new WebSocketDecoder());
        pipeline.addLast(webSocketHeartbeatHandler);
        pipeline.addLast(messageHandler);
        pipeline.addLast(logoutHandler);
        pipeline.addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
                System.out.printf(textWebSocketFrame.text());
            }
        });
    }
}
