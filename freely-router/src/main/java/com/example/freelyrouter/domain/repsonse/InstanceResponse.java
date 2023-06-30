package com.example.freelyrouter.domain.repsonse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstanceResponse {
    Integer Port;
    String Addr;
}
