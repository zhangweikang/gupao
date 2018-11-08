package my.project.cloud.feign.api.hystrix;

import my.project.cloud.feign.api.service.PersonService;
import my.project.cloud.feign.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * @author ZhangWeiKang
 * @create 2018/8/21
 */
@Component
public class PersonServiceFallBack implements PersonService {
    @Override
    public void save(@RequestBody Person person) {
        System.out.println(" 服务熔断 save ");
    }

    @Override
    public Collection<Person> findAll() {
        System.out.println(" 服务熔断 findAll ");
        return null;
    }
}
