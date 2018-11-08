package my.project.cloud.feign.consumer.config;

import my.project.cloud.feign.consumer.ribbon.SelectFirstServerRule;
import org.springframework.context.annotation.Bean;

/**
 * Ribbon 配置类
 *
 * @author ZhangWeiKang
 * @create 2018/8/21
 */
//@Configuration
public class RibbonConfig {
    @Bean
    public SelectFirstServerRule getSelectFirstServerRule(){
        return new SelectFirstServerRule();
    }
}
