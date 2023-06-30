package com.example.freelyrouter.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrivateChatRequest {
    private String fromId;
    private String toId;
    private String content;
    private Integer msgType;
    private LocalDateTime sendTime;
}
