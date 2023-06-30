package com.example.freelyrouter.service.impl;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.example.freelyrouter.domain.repsonse.InstanceResponse;
import com.example.freelyrouter.service.ImNacosService;

import org.freely.commom.contans.NacosConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ImNacosServiceImpl implements ImNacosService {

    @NacosInjected
    private NamingService namingService;

    private AtomicInteger counter = new AtomicInteger(0);

    /**
     * 轮询获取服务实例
     * @param serviceName
     * @return
     * @throws NacosException
     */
    private Instance getPollingService(String serviceName) throws NacosException {
        if (counter.get()==Byte.MAX_VALUE){
            counter.set(0);
        }
        List<Instance> instances= namingService.selectInstances(serviceName,true);
        int index = counter.getAndIncrement() % instances.size();
        return instances.get(index);
    }
    @Override
    public InstanceResponse getImServerInstance() throws NacosException {
        Instance instance= getPollingService(NacosConstants.NacosImServiceName);
        return InstanceResponse.builder()
                                .Port(instance.getPort())
                                .Addr(instance.getIp())
                                .build();
    }
}
