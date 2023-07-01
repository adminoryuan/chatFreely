package com.freely.service;

import com.freely.domain.entity.SysUser;

public interface TokenService {
    String CreateToken(SysUser sysUser);
}
