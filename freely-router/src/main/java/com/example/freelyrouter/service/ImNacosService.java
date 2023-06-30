package com.example.freelyrouter.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.example.freelyrouter.domain.repsonse.InstanceResponse;

public interface ImNacosService {

    InstanceResponse getImServerInstance() throws NacosException;
}
