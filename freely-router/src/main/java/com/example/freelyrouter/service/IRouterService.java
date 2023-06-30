package com.example.freelyrouter.service;

public interface IRouterService {
    /**
     *
     * @param uid 用户编号
     * @return 返回所链接的im地址
     */
    String getOneRouter(String uid);
}
