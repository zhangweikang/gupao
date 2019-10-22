package my.project.cloud.spring.cloud.demo.configuration;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 外部化配置
 *
 * @author zhangweikang
 * @date 2019/10/21
 */
public class MyPropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String,Object> myProperties = new HashMap<>();
        myProperties.put("server.port","18802");
        return new MapPropertySource("myProperties",myProperties);
    }
}
