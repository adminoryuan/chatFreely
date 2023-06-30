package org.freely.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.freely.netty.codec.WebSocketEncoder;
import org.freely.netty.handler.IMServerWebSocketHandler;
import org.freely.netty.handler.WebSocketAuthHandler;
import org.freely.netty.handler.WebSocketHeartbeatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

public class ServerInitializer extends ChannelInitializer<Channel> {
    @Autowired
    private WebSocketAuthHandler webSocketAuthHandler;
    @Autowired
    private  IMServerWebSocketHandler imServerWebSocketHandler;

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
        pipeline.addLast(webSocketHeartbeatHandler);
        pipeline.addLast(imServerWebSocketHandler);

        pipeline.addLast(new WebSocketEncoder());
    }
}
