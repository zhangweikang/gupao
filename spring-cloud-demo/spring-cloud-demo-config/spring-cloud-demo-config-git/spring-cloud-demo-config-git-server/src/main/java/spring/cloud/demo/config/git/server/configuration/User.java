package spring.cloud.demo.config.git.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangweikang
 * @date 2019/10/22
 */
@ConfigurationProperties(prefix = "user")
public class User {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
