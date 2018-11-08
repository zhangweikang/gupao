package my.project.boot.start;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 微服务项目应用启动
 *
 * @author ZhangWeiKang
 * @create 2018/7/19
 */
@SpringBootApplication
public class MicroservicesProjectApplication {

    public static void main(String[] args) {
        //基本写法
        //SpringApplication springApplication = new SpringApplication(MicroservicesProjectApplication.class);
        //Map<String,Object> properties = new HashMap<>();
        //properties.put("server.port",0);
        //springApplication.setDefaultProperties(properties);
        //springApplication.setWebApplicationType(WebApplicationType.NONE);
        //springApplication.run(args);



        //builder方式启动
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MicroservicesProjectApplication.class);
        springApplicationBuilder.properties("server.port=0");
        springApplicationBuilder.listeners(applicationEvent -> {
                    System.out.println("当前事件 = " + applicationEvent.getClass().getSimpleName());
                });
        springApplicationBuilder.run(args);
    }
}
