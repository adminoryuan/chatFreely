package org.freely.netty.entity.protocol;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.freely.netty.enums.PacketType;

@Data
public class PacketHeader {
    PacketType type;

    JsonNode body;

}
