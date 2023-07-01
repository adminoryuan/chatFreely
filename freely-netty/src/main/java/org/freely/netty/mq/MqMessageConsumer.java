package org.freely.netty.mq;

import lombok.extern.slf4j.Slf4j;
import org.freely.commom.utls.JsonUtils;
import org.freely.netty.session.SessionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@Slf4j
public class MqMessageConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}
