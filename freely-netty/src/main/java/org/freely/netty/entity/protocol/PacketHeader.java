package org.freely.netty.entity.protocol;

import lombok.Data;
import org.freely.netty.enums.PacketType;

@Data
public class PacketHeader {
    PacketType type;

    Object body;

}
