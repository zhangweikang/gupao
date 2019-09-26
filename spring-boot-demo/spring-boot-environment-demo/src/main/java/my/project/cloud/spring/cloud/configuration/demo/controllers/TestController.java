package my.project.cloud.spring.cloud.configuration.demo.controllers;

import my.project.cloud.spring.cloud.configuration.demo.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangweikang
 * @date 2019/9/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Config configuration;

    @RequestMapping("/getRandomInt")
    public String test(){
        return configuration.getRandomInt()+"";
    }
    @RequestMapping("/getRandomLong")
    public String test1(){
        return configuration.getRandomLong()+"";
    }
    @RequestMapping("/getUserName")
    public String test2(){
        return configuration.getUserName();
    }
}
