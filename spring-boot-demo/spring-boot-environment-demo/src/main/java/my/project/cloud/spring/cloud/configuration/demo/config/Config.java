package my.project.cloud.spring.cloud.configuration.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhangweikang
 * @date 2019/9/24
 */
@Configuration
@PropertySource(value = "classpath:test.properties")
public class Config {

    @Value("${random.int}")
    private int randomInt;
    @Value("${random.long}")
    private long randomLong;
    @Value("${user.name}")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public int getRandomInt() {
        return randomInt;
    }

    public long getRandomLong() {
        return randomLong;
    }
}
