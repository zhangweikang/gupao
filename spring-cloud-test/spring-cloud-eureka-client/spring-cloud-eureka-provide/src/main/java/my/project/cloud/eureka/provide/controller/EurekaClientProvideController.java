package my.project.cloud.eureka.provide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class EurekaClientProvideController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String serverName;


    @GetMapping("/get/server")
    public Map<String,List<String>> getServerName(){
        Map<String,List<String>> result = new HashMap<>();

        List<String> services = discoveryClient.getServices();

        result.put("serverName",services);

        System.out.println("serverName = " + serverName);
        return result;
    }
}
