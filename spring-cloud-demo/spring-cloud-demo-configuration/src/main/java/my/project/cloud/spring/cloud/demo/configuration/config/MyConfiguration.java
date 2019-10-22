package my.project.cloud.spring.cloud.demo.configuration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhangweikang
 * @date 2019/10/21
 */
@Configuration
@Import(MyImportSelector.class)
public class MyConfiguration {

    /*@Bean
    public MyPropertySourceLocator getMyPropertySourceLocator(){
        return new MyPropertySourceLocator();
    }*/
}
