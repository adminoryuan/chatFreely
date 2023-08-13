package org.freely.netty.entity.protocol.impl.request;

import lombok.Data;
import org.freely.netty.entity.protocol.PacketBody;
import org.freely.netty.enums.PacketType;

import java.io.Serializable;

@Data
public class LogoutPacket extends PacketBody implements Serializable {
    String token;
}
