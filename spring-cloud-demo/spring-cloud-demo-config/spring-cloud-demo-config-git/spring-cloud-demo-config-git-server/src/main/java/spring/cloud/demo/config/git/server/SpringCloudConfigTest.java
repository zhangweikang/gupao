package spring.cloud.demo.config.git.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhangweikang
 * @date 2019/10/22
 */
@SpringCloudApplication
@EnableConfigServer
public class SpringCloudConfigTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigTest.class);
    }
}
