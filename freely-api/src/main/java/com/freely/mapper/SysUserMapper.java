package com.freely.mapper;

import com.freely.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {

    SysUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Integer updateSysUser(SysUser sysUser);
}
