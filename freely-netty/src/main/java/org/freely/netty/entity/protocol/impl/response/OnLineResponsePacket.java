package org.freely.netty.entity.protocol.impl.response;

import lombok.Builder;
import lombok.Data;
import org.freely.netty.entity.protocol.PacketBody;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OnLineResponsePacket extends PacketBody {
    @Data
    @Builder
    public static class OnLineResponseItem{
        String userName;
        LocalDateTime LoginTime;
    }
    List user;
}
