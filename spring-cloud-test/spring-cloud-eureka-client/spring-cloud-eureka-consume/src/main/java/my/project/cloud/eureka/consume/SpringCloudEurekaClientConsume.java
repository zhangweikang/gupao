package my.project.cloud.eureka.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * EurekaClient
 *
 * @author ZhangWeiKang
 * @create 2018/8/7
 */
@SpringCloudApplication
public class SpringCloudEurekaClientConsume {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientConsume.class, args);
    }
}
