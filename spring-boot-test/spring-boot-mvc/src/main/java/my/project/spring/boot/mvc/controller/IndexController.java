package my.project.spring.boot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index
 *
 * @author ZhangWeiKang
 * @create 2018/7/22
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/"})
    public String index(Model model){

        model.addAttribute("message","hello,Thymeleaf");
        return "index";
    }
}
