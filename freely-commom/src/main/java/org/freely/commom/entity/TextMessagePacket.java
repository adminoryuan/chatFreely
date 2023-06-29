package org.freely.commom.entity;

import lombok.Data;

@Data
public class TextMessagePacket extends BaseMessage{
    /**
     * 消息体
     */
    private String content;
}
