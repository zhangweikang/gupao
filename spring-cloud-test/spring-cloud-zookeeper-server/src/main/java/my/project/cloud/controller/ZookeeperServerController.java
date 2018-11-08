package my.project.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author ZhangWeiKang
 * @create 2018/8/7
 */
@RestController
public class ZookeeperServerController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/applicationName")
    public String getApplicationName(){
        System.out.println("applicationName = " + applicationName);
        return applicationName;
    }
}
