package org.freely.commom.entity;

import lombok.Data;

import java.time.LocalDateTime;

public class BaseMessage {
    /**
     * 消息序列号
     */
    private Long reqNo;

    /**
     * 消息传送类型
     */
    private Integer reqType;

    /**
     * 区分私聊或者群聊
     */
    private Integer rangeType;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 接受用户
     */
    private Long acceptId;

}
