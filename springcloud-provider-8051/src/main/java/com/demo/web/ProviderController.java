package com.demo.web;

import com.demo.model.Users;
import com.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    public List<Users> findUserByLikeName(@PathVariable(value = "name", required = false) String name){
        List<Users> userByLikeName = service.findUserByLikeName(name);
        for (Users user:userByLikeName) {
            user.setUserName(user.getUserName() + "---" + port);
        }
        return userByLikeName;
    }

    /**
     * 获取注册表信息
     * @return
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public List<String> getServiceInfo(){
        List<String> services = client.getServices();
        for (String name : services) {
            List<ServiceInstance> instances = client.getInstances(name);
            for (ServiceInstance instance :instances) {
                String host = instance.getHost();
                String instanceId = instance.getInstanceId();
                Map<String, String> metadata = instance.getMetadata();
                int port = instance.getPort();
                String scheme = instance.getScheme();
                String serviceId = instance.getServiceId();
                URI uri = instance.getUri();
                String uri2 = uri.toASCIIString();
                System.out.println("host = " + host);
                System.out.println("instanceId = " + instanceId);
                System.out.println("metadata = " + metadata);
                System.out.println("port = " + port);
                System.out.println("scheme = " + scheme);
                System.out.println("serviceId = " + serviceId);
                System.out.println("uri2 = " + uri2);
                System.out.println("-------------------------------------------");
            }
        }
        return services;
    }


}
