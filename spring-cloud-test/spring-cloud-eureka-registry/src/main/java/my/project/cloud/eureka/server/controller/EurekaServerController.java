package my.project.cloud.eureka.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author ZhangWeiKang
 * @create 2018/8/11
 */
@RestController
public class EurekaServerController {

    @Value("${spring.application.name}")
    private String serverName;

    @GetMapping("/serverName")
    public String getServerName(){
        return serverName;
    }
}
