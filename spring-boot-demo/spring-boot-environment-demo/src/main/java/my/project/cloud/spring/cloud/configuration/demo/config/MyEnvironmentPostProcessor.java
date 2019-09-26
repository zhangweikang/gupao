package my.project.cloud.spring.cloud.configuration.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义外部化配置解析器
 *
 * @author zhangweikang
 * @date 2019/9/26
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final int DEAFULE_ORDER = Ordered.HIGHEST_PRECEDENCE + 20;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String,Object> map = new HashMap<>();
        map.put("server.port","18083");
        PropertySource propertySource = new MapPropertySource("userProperties",map);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(propertySource);
    }

    @Override
    public int getOrder() {
        return DEAFULE_ORDER;
    }
}
