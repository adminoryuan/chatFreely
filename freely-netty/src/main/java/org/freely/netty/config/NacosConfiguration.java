package org.freely.netty.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848",namespace = "e3922e53-079b-4910-a9a7-04d4aafb622b"))
public class NacosConfiguration  {
    @NacosInjected
    private NamingService namingService;

    @Value("${nacos.server.address}")
    private String addr;
    @Value("${nacos.server.namespace}")
    private String namespace;

    @Value("${nacos.server.name}")
    private String serverName;


    public NacosConfiguration() {
    }

    @PostConstruct
    public void registerService() throws NacosException {
        Instance instance = new Instance();
        instance.setIp(addr); // 设置服务实例的IP
        instance.setPort(8080); // 设置服务实例的端口
        instance.setServiceName(namespace); // 设置服务名称

        // 注册服务实例
        namingService.registerInstance(serverName, instance);
    }

}
