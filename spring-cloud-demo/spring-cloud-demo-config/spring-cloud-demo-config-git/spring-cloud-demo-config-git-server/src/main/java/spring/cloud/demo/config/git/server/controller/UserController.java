package spring.cloud.demo.config.git.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.cloud.demo.config.git.server.configuration.User;

/**
 * @author zhangweikang
 * @date 2019/10/22
 */
@RestController
@RequestMapping("demo")
public class UserController {

    @Autowired
    private User user;

    @GetMapping("user")
    public User getUser(){
        return user;
    }
}
