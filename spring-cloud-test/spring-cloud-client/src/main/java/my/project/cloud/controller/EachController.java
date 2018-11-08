package my.project.cloud.controller;

import my.project.cloud.config.RefreshScopeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取远程属性
 *
 * @author ZhangWeiKang
 * @create 2018/8/2
 */
@RestController
public class EachController {

    @Autowired
    private Environment environment;

    @Autowired
    private RefreshScopeConfig refreshScopeConfig;

    @GetMapping("/get")
    private Map<String,Object> getName(){
        Map<String,Object> result = new HashMap<>();
        result.put("name",refreshScopeConfig.getName());

        String name = environment.getProperty("name");
        result.put("environmentName",name);
        return result;
    }
}
