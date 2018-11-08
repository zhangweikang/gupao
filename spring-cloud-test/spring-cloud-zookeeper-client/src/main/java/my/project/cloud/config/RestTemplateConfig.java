package my.project.cloud.config;

import my.project.cloud.commons.ILoadBalances;
import my.project.cloud.interceptors.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 自定义RestTemplate
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private ILoadBalances loadBalances;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    @LoadBalanced
    private RestTemplate lbRestTemplate;
    @Value("${request.server.name}")
    private String serverName;

    public String getStringBody(String serviceName,String business) throws Exception{
        String selectUrl = loadBalances.selectOne(serviceName);
        return restTemplate.getForEntity(selectUrl+"/"+business,String.class).getBody();
    }

    @Bean
    //@Primary
    public RestTemplateInterceptor getHttpRequestInterceptor(){
        return new RestTemplateInterceptor();
    }

    @Primary
    @Bean
    @Autowired
    public RestTemplate getRestTemplate(RestTemplateInterceptor restTemplateInterceptor){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(restTemplateInterceptor));
        return restTemplate;
    }
    /*@Bean
    @Primary
    @Qualifier
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }*/

    @Bean
    @LoadBalanced
    public RestTemplate getLbRestTemplate(){
        return new RestTemplate();
    }

    /*@Bean
    @Autowired
    public Object customizer(@Qualifier Collection<RestTemplate> restTemplates,@Qualifier ClientHttpRequestInterceptor restTemplateInterceptor){
        restTemplates.forEach(restTemplate1 -> restTemplate1.setInterceptors(Arrays.asList(restTemplateInterceptor)));
        return new Object();
    }*/

    public String invoker(String business){
        return restTemplate.getForEntity("/"+serverName+"/"+business,String.class).getBody();
    }

    public String lbInvoker(String business){
        //String selectUrl = loadBalances.selectOne(serverName);
        return lbRestTemplate.getForEntity("http://"+serverName+"/"+business,String.class).getBody();
    }
}
