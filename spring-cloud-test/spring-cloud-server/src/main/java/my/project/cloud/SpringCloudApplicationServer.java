package my.project.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 */
@SpringCloudApplication
@EnableConfigServer
public class SpringCloudApplicationServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplicationServer.class,args);
    }
}