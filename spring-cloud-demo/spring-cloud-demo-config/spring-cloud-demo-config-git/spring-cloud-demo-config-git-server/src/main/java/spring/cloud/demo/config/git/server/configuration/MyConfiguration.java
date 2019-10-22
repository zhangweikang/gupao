package spring.cloud.demo.config.git.server.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangweikang
 * @date 2019/10/22
 */
@Configuration
@EnableConfigurationProperties(User.class)
public class MyConfiguration {

}
