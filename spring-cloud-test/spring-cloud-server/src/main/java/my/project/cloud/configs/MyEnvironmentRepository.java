package my.project.cloud.configs;

/**
 * 自定义EnvironmentRepository
 *
 * @author ZhangWeiKang
 * @create 2018/8/3
 */
/*@Configuration
public class MyEnvironmentRepository implements EnvironmentRepository,Ordered {

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application,profile);
        Map<String,Object> map =new HashMap<>();
        map.put("name","zhangweikang-my");
        PropertySource propertySource = new PropertySource("MyEnvironmentRepository",map);
        environment.add(propertySource);
        environment.setState("UP");
        environment.setProfiles(new String [] {"dev"});
        environment.setName("test");
        environment.setVersion("my-version");
        return environment;
    }

    @Override
    public int getOrder() {
        return 21;
    }
}*/
