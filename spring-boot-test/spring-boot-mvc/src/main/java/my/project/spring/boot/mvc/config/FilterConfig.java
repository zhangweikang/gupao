package my.project.spring.boot.mvc.config;

import my.project.spring.boot.mvc.filter.DemoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 *
 * @author ZhangWeiKang
 * @create 2018/7/26
 */
@Configuration
public class FilterConfig {
    @Bean
    FilterRegistrationBean<DemoFilter> registerDemoFilter(){
        FilterRegistrationBean<DemoFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new DemoFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("paramName", "paramValue");
        filterRegistrationBean.setName("DemoFilter");
        return filterRegistrationBean;
    }
}
