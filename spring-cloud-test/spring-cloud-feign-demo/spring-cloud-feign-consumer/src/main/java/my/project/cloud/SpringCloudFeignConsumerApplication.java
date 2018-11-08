package my.project.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 */
@SpringCloudApplication
//@SpringBootApplication
@EnableFeignClients
//@RibbonClient(name = "spring-cloud-feign-provider" ,configuration = RibbonConfig.class)
public class SpringCloudFeignConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFeignConsumerApplication.class,args);
    }
}
