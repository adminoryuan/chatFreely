package org.freely.netty.service.impl;

import io.netty.channel.Channel;
import org.freely.commom.contans.RouteConstants;
import org.freely.commom.entity.dto.TokenDto;
import org.freely.netty.service.IRouterService;
import org.freely.netty.session.SessionUtils;
import org.freely.netty.utls.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RouterServiceImpl implements IRouterService {

    @Value("im.server.address")
    private String address;

    @Value("im.server.port")
    private String port;
    @Override
    public void addRouter(TokenDto dto, Channel channel) throws IOException {
        RedisUtils.set(RouteConstants.MergeRoute(dto.getUserName()), String.format("{0}:{1}",address,port));
        SessionUtils.bindSession(dto.getUserName(),channel);
    }
}
