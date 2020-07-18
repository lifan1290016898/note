package com.demo.configuration.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义负载均衡策略
 *      排除指定端口的server后，随机一个server
 */
public class CustomIRule implements IRule {

    private ILoadBalancer lb;

    private List<Integer> ports;

    public CustomIRule(List<Integer> ports){
        this.ports = ports;
    }

    /**
     * 无参构造必不可少
     */
    public CustomIRule(){

    }


    @Override
    public Server choose(Object key) {
        // 获得所有up状态的server
        List<Server> upServer = lb.getReachableServers();

        List<Server> server = new ArrayList<>();
        // 排除指定端口的server
        for (Server s :upServer) {
            if(!this.ports.contains(s.getPort())){
                server.add(s);
            }
        }
        // 随机选择一个server
        int index = new Random().nextInt(server.size());
        return server.get(index);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }

}
