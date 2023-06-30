package com.example.freelyrouter.service.impl;

import com.example.freelyrouter.domain.request.PrivateChatRequest;
import com.example.freelyrouter.service.IChatService;
import com.example.freelyrouter.service.IRouterService;
import org.freely.commom.contans.RabbitMqConstants;
import org.freely.commom.utls.JsonUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    private IRouterService routerService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public boolean publishPrivateMeg(PrivateChatRequest request) {
        String reqJsonStr=JsonUtils.toJson(request);
        String address= routerService.getOneRouter(request.getToId());
        String queueName=RabbitMqConstants.MergeQueue(address);
        rabbitTemplate.convertAndSend(RabbitMqConstants.ImClusterExchangeName,queueName,reqJsonStr,new CorrelationData(UUID.randomUUID().toString()));
        return true;
    }
}
