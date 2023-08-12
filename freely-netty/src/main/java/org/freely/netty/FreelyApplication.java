package org.freely.netty;

import com.alibaba.nacos.api.exception.NacosException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.freely.commom.entity.dto.TokenDto;
import org.freely.netty.utls.RedisUtils;

import java.io.IOException;

@Slf4j
public class FreelyApplication {
    private static final Integer PORT=10000;
    public static void main(String[] args) throws InterruptedException, NacosException, IOException {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setUserName("Zhangsan");
        tokenDto.setUserId(1l);
        RedisUtils.set("tokentoken2",tokenDto);

        //spring初始化
        SpringLoader.Init();
        //netty初始化
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).childOption(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(SpringLoader.getBean(ServerInitializer.class));
        ChannelFuture channelFuture = bind(serverBootstrap, PORT, false);
        log.info(String.format("{0} 启动成功",PORT));
        channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }).sync();
    }

    private static ChannelFuture bind(ServerBootstrap serverBootstrap, int port, boolean inc) {
        return serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    log.info("server success");
                } else {
                    if (inc) {
                        int newPort = port + 1;
                        bind(serverBootstrap, newPort, inc);
                        log.info("try new port " + newPort);
                    }
                }
            }
        });
    }
}