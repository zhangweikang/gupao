package my.project.cloud.config;

import my.project.cloud.commons.PollingLoadBalances;
import my.project.cloud.commons.RandomLoadBalances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LoadBalances 配置类
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
@Configuration
public class LoadBalancesConfig {

    @Autowired
    private ServiceProductConfig serviceProductConfig;

    @Bean
    @ConditionalOnProperty(value = "my.config.loadBalances",havingValue = "random",matchIfMissing = true)
    public RandomLoadBalances getDefaultLoadBalances(){
        return new RandomLoadBalances(serviceProductConfig);
    }

    @Bean
    @ConditionalOnProperty(value = "my.config.loadBalances",havingValue = "polling")
    public PollingLoadBalances getPollingLoadBalances(){
        return new PollingLoadBalances(serviceProductConfig);
    }
}
