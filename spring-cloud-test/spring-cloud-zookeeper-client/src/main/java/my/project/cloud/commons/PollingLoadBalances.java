package my.project.cloud.commons;

import my.project.cloud.config.ServiceProductConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * 轮询,负载均衡算法
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
public class PollingLoadBalances extends AbstractLoadBalances implements ILoadBalances {

    private int index = 0;

    @Autowired
    public PollingLoadBalances(ServiceProductConfig serviceProductConfig) {
        super(serviceProductConfig);
    }

    @Override
    public synchronized <T> T selectOne(String serviceName) {
        return super.selectOne(serviceName);
    }

    @Override
    <T> T doSelect(Collection<T> collection) {

        T[] ts = (T[]) collection.toArray();
        index++;

        if (index > collection.size() - 1) {
            index = 0;
        }
        return ts[index];
    }
}
