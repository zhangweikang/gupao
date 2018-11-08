package my.project.cloud.eureka.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EurekaClient
 *
 * @author ZhangWeiKang
 * @create 2018/8/7
 */
@SpringCloudApplication
@EnableDiscoveryClient
public class SpringCloudEurekaClientProvide {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientProvide.class, args);
    }
}
