package org.freely.netty.service;


import io.netty.channel.Channel;
import jdk.nashorn.internal.parser.Token;
import org.freely.commom.entity.dto.TokenDto;

import java.io.IOException;

public interface IRouterService {
     void addRouter(TokenDto dto, Channel channel) throws IOException;
}
