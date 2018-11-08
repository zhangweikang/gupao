package my.project.cloud.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import my.project.cloud.config.RefreshScopeConfig;
import my.project.cloud.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取远程属性
 *
 * @author ZhangWeiKang
 * @create 2018/8/2
 */
@RestController
public class SpringCloudZookeeperClientController {

    @Autowired
    private RefreshScopeConfig refreshScopeConfig;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/lb/services/{business}")
    @HystrixCommand(fallbackMethod = "fallbackGetlbName")
    public Map<String,Object> getlbName(@PathVariable String business){
        Map<String,Object> result = new HashMap<>();
        String stringBody = restTemplateConfig.lbInvoker(business);
        //String stringBody = restTemplate.getForEntity("aaaa", String.class).getBody();
        result.put("name",stringBody);
        result.put("port","loadBalancer");
        System.out.println(" Spring cloud client ");
        return result;
    }

    @GetMapping("/services/{business}")
    @HystrixCommand(fallbackMethod = "fallbackGetName")
    public Map<String,Object> getName(@PathVariable String business){
        Map<String,Object> result = new HashMap<>();
        //System.out.println(1/0);
        String stringBody = restTemplateConfig.invoker(business);
        //String stringBody = restTemplate.getForEntity("aaaa", String.class).getBody();
        result.put("name",stringBody);
        System.out.println(" Spring cloud client ");
        return result;
    }

    @GetMapping("/get")
    public Map<String,Object> getAllName(){
        Map<String,Object> result = new HashMap<>();
        result.put("name",refreshScopeConfig.getName());
        return result;
    }

    @GetMapping("/services/{serviceName}/{business}")
    @HystrixCommand(fallbackMethod = "fallbackGetName")
    public Map<String,Object> getStringName(@PathVariable String business,
                                       @PathVariable String serviceName) throws Exception {
        Map<String,Object> result = new HashMap<>();

        String stringBody = restTemplateConfig.getStringBody(serviceName,business);
        result.put("name",stringBody);
        return result;
    }
    public Map<String,Object> fallbackGetName(String business){
        Map<String,Object> result = new HashMap<>();
        result.put("error","fail");
        return result;
    }

    public Map<String,Object> fallbackGetlbName(String business){
        Map<String,Object> result = new HashMap<>();
        result.put("error","fail lb name");
        return result;
    }

    @GetMapping("/services/command")
    public String getNameHystrixCommand(){
        return new LbNameHystrixCommand().execute();
    }

    private class LbNameHystrixCommand extends com.netflix.hystrix.HystrixCommand<String>{

        public LbNameHystrixCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("LoadBalancerName"));
        }

        @Override
        protected String run() throws Exception {

            System.out.println(1/0);

            return "Hello,World";
        }

        @Override
        protected String getFallback() {
            return "error fail";
        }
    }
}
