package my.project.cloud.commons;

import my.project.cloud.config.ServiceProductConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Random;

/**
 * 随机负载算法
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
public class RandomLoadBalances extends AbstractLoadBalances implements ILoadBalances {
    @Autowired
    public RandomLoadBalances(ServiceProductConfig serviceProductConfig) {
        super(serviceProductConfig);
    }

    @Override
    public <T> T selectOne(String serviceName) {
        return super.selectOne(serviceName);
    }

    @Override
    <T> T doSelect(Collection<T> collection) {

        Random random = new Random();
        int index = random.nextInt(3);

        T[] objects = (T[])collection.toArray();

        return objects[index];
    }
}
