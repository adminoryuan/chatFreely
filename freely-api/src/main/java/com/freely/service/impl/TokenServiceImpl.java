package com.freely.service.impl;

import com.freely.component.RedisCache;
import com.freely.domain.entity.SysUser;
import com.freely.service.TokenService;
import org.freely.commom.contans.RedisConstants;
import org.freely.commom.utls.IdUtils;
import org.freely.commom.utls.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisCache redisCache;
    @Override
    public String CreateToken(SysUser sysUser) {
        String token= IdUtils.randomUUID();
        redisCache.setCacheObject(RedisConstants.LOGIN_TOKEN_KEY+token,sysUser);
        return token;
    }
}
