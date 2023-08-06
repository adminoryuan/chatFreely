package org.freely.netty.entity.protocol.impl;

import lombok.Data;
import org.freely.netty.entity.protocol.PacketBody;
import org.freely.netty.enums.PacketType;

import java.io.Serializable;

@Data
public class LogoutPacket implements Serializable, PacketBody {
    String token;
}