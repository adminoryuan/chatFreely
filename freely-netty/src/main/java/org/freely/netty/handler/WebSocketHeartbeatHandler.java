package org.freely.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
@Component
public class WebSocketHeartbeatHandler extends ChannelInboundHandlerAdapter {
    private static final String HEARTBEAT_MESSAGE = "Heartbeat";
    private static final long HEARTBEAT_INTERVAL_SECONDS = 10;
    private ScheduledExecutorService executorService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            sendHeartbeat(ctx);
        }, HEARTBEAT_INTERVAL_SECONDS, HEARTBEAT_INTERVAL_SECONDS, TimeUnit.SECONDS);
        super.channelActive(ctx);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        stopHeartbeat();
        super.channelInactive(ctx);
    }
    private void sendHeartbeat(ChannelHandlerContext ctx) {
        TextWebSocketFrame heartbeatFrame = new TextWebSocketFrame(HEARTBEAT_MESSAGE);
        ctx.writeAndFlush(heartbeatFrame);
    }
    private void stopHeartbeat() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
