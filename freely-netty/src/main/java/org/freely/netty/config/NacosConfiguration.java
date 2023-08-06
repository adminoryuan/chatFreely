package org.freely.netty.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;

import org.freely.commom.contans.NacosConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "124.221.132.50",namespace = "ad927766-25e2-4f8e-a386-e303cb641d3e"))
public class NacosConfiguration  {
    @NacosInjected
    private NamingService namingService;

    @Value("${im.server.address}")
    private String addr;

    @Value("${im.server.port}")
    private Integer port;

    @Value("${nacos.server.namespace}")
    private String namespace;

    public NacosConfiguration() {
    }

    @PostConstruct
    public void registerService() throws NacosException {
        Instance instance = new Instance();
        instance.setIp(addr); // 设置服务实例的IP
        instance.setPort(port); // 设置服务实例的端口
        instance.setServiceName(NacosConstants.NacosImServiceName); // 设置服务名称

        // 注册服务实例
        namingService.registerInstance(NacosConstants.NacosImServiceName, instance);
    }

}
