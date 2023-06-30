package com.example.freelyrouter.service.impl;

import com.example.freelyrouter.component.RedisCache;
import com.example.freelyrouter.service.IRouterService;
import org.freely.commom.contans.RouteConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouterServiceImpl implements IRouterService {
    @Autowired
    RedisCache cache;

    @Override
    public String getOneRouter(String uid) {
        return cache.getCacheObject(RouteConstants.MergeRoute(uid));
    }
}
