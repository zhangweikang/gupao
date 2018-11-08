package my.project.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务service配置类
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
@Component
public class ServiceProductConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    private volatile Map<String,List<String>> productUrl = new HashMap<>();

    private DiscoveryClient discoveryClient;

    @Autowired
    public ServiceProductConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        findProductUrl(discoveryClient);
        System.out.println("初始化 productUrl.size = " + this.productUrl.size());
    }

    @Scheduled(fixedRate = 30*1000)
    public void updateProductUrl(){
        System.out.println(" 更新服务数据 ------  ");
        findProductUrl(discoveryClient);
        System.out.println("更新 productUrl.size = " + this.productUrl.size());
    }

    private void findProductUrl(DiscoveryClient discoveryClient){
        Map<String,List<String>> oldProductUrl = this.productUrl;

        Map<String,List<String>> newProductUrl = new HashMap<>();
        List<String> serviceIds = discoveryClient.getServices();

        StringBuffer stringBuffer = new StringBuffer();
        serviceIds.forEach(serviceId -> {
            List<String> collect = discoveryClient.getInstances(serviceId).stream().map(serviceInstance -> {
                stringBuffer.setLength(0);
                stringBuffer.append(serviceInstance.isSecure() ? "https://" : "http://");
                stringBuffer.append(serviceInstance.getHost());
                stringBuffer.append(":");
                stringBuffer.append(serviceInstance.getPort());
                return stringBuffer.toString();
            }).collect(Collectors.toList());
            newProductUrl.put(serviceId,collect);
        });

        this.productUrl = newProductUrl;
        oldProductUrl.clear();


        /*for (String serviceId : serviceIds) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance serviceInstance : serviceInstances) {
                stringBuffer.setLength(0);
                stringBuffer.append(serviceInstance.isSecure() ? "https://" : "http://");
                stringBuffer.append(serviceInstance.getHost());
                stringBuffer.append(":");
                stringBuffer.append(serviceInstance.getPort());
                productUrl.add(stringBuffer.toString());
            }
        }*/
    }

    public Map<String,List<String>> getProductUrl() {
        return new HashMap<>(this.productUrl);
    }
}
