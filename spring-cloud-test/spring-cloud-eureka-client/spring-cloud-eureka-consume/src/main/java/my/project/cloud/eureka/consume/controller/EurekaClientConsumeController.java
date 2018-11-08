package my.project.cloud.eureka.consume.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller
 *
 * @author ZhangWeiKang
 * @create 2018/8/11
 */
@RestController
public class EurekaClientConsumeController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${project.eureka.provide.name}")
    private String eurekaServerName;


    @GetMapping("/get/server")
    public Map<String,List<String>> getServerName(){
        Map<String,List<String>> result = new HashMap<>();
        List<String> services = discoveryClient.getServices();
        result.put("serverName",services);
        return result;
    }

    @GetMapping("/names")
    @HystrixCommand(fallbackMethod = "getNameFailBack")
    public Map getName(){
        Map forObject = restTemplate.getForObject("http://" + eurekaServerName + "/get/server", Map.class);
        return forObject;
    }
    @GetMapping("/names/test")
    @HystrixCommand(fallbackMethod = "getNameTestFailBack")
    public Map getNameTest(){
        Map forObject = restTemplate.getForObject("http://" + eurekaServerName + "/get/server", Map.class);
        return forObject;
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public Map getNameFailBack(){
        Map<String,Object> failBack = new HashMap<>();
        failBack.put("error","Fail");
        return failBack;
    }
    public Map getNameTestFailBack(){
        Map<String,Object> failBack = new HashMap<>();
        failBack.put("error","Fail Test");
        return failBack;
    }
}
