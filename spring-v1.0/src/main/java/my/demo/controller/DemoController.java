package my.demo.controller;

import my.demo.service.IDemoService;
import my.spring.annotation.Autowried;
import my.spring.annotation.Controller;

/**
 * controller
 *
 * @author ZhangWeiKang
 * @create 2018/7/24
 */
@Controller
public class DemoController {

    @Autowried
    private IDemoService demoService;

    public void query(){
        demoService.query("tom");
    }

}
