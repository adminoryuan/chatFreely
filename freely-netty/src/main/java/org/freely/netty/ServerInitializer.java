package org.freely.netty;

import io.netty.channel.*;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import org.freely.netty.codec.WebSocketDecoder;
import org.freely.netty.codec.WebSocketEncoder;
import org.freely.netty.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

public class ServerInitializer extends ChannelInitializer<Channel> {
    @Autowired
    private FreelyAuthHandler webSocketAuthHandler;
    @Autowired
    private FreelyLogoutHandler logoutHandler;
    @Autowired
    private FreelyMessageHandler messageHandler;
    @Value("${websocket.router}")
    private  String WebSocketRouter;
    @Autowired
    private FreelyHeartbeatHandler webSocketHeartbeatHandler;
    @Autowired
    private FreelyOnLineHandler FreelyOnLineHandler;
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
        pipeline.addLast(FreelyOnLineHandler);
        pipeline.addLast(logoutHandler);
    }
}
