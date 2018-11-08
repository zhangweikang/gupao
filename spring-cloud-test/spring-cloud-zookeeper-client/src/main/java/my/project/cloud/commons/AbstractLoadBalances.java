package my.project.cloud.commons;

import my.project.cloud.config.ServiceProductConfig;

import java.util.Collection;

/**
 * 抽象负载均衡算法
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
public abstract class AbstractLoadBalances {

    private ServiceProductConfig serviceProductConfig;

    public AbstractLoadBalances(ServiceProductConfig serviceProductConfig) {
        this.serviceProductConfig = serviceProductConfig;
    }

    public <T> T selectOne(String serviceName){
        return (T)doSelect(serviceProductConfig.getProductUrl().get(serviceName));
    }

    abstract <T> T doSelect(Collection<T> collection);
}
