package my.project.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaServer
 *
 * @author ZhangWeiKang
 * @create 2018/8/7
 */
@SpringCloudApplication
@EnableEurekaServer
public class SpringCloudEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServer.class,args);
    }
}
