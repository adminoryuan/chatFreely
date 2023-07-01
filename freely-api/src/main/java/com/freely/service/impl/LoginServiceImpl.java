package com.freely.service.impl;

import com.freely.controller.GlobalException;
import com.freely.domain.entity.SysUser;
import com.freely.domain.request.LoginRequest;
import com.freely.domain.response.LoginResponse;
import com.freely.mapper.SysUserMapper;
import com.freely.service.LoginService;
import com.freely.service.TokenService;
import org.freely.commom.core.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TokenService tokenService;
    @Override
    public LoginResponse Login(LoginRequest request) {
        SysUser user= sysUserMapper.findByUsernameAndPassword(request.getUserName(),request.getPassWord());
        if (user==null){
            throw new CustomException("账号或密码错误");
        }
        String token= tokenService.CreateToken(user);
        SysUser sysUser= SysUser.builder()
                                 .id(user.getId())
                                 .loginDate(LocalDateTime.now())
                                 .loginIp("127.0.0.1")
                                 .build();
        sysUserMapper.updateSysUser(sysUser);
        return LoginResponse.builder().token(token).build();
    }
}
