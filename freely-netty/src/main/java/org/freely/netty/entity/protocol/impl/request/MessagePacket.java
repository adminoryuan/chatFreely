package org.freely.netty.entity.protocol.impl.request;

import com.sun.org.apache.xml.internal.serialize.Serializer;
import lombok.Data;
import org.freely.netty.entity.protocol.PacketBody;
import org.freely.netty.enums.ChatType;
import org.freely.netty.enums.MessageType;
import org.freely.netty.utls.IdUtils;

import java.awt.*;
import java.io.Serializable;

@Data
public class MessagePacket extends PacketBody implements Serializable {
    private String id; // 唯一消息ID
    private MessageType type;
    private String content;
    private String sender;
    private String recipient;
    private String group_id;
    private String url;
    private String timestamp;
    private ChatType chatType;

    public MessagePacket() {
        this.id= IdUtils.randomUUID();
    }
}
