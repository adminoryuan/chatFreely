package com.freely.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nickName;

    private String userName;

    private String passWord;

    private Integer sex;

    private String avatar;

    private String  loginIp;

    private LocalDateTime loginDate;

}
