package my.project.cloud;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
@SpringCloudApplication
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext annotationConfig = new AnnotationConfigApplicationContext();
        annotationConfig.setId("zhangweikang");
        annotationConfig.refresh();

        new SpringApplicationBuilder(App.class)
                .parent(annotationConfig)
                .run(args);
    }

    public static class MyPropertySourceLocator implements PropertySourceLocator {
        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<>();
            source.put("server.port","9090");
            source.put("management.server.port","9090");
            MapPropertySource propertySource =
                    new MapPropertySource("my-property-source", source);
            return propertySource;
        }
    }

}
