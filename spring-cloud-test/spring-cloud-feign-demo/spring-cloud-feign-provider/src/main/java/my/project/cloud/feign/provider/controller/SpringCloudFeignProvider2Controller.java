package my.project.cloud.feign.provider.controller;

import my.project.cloud.feign.api.service.Person2Service;
import my.project.cloud.feign.domain.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Person Provider Controller
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
@RestController
public class SpringCloudFeignProvider2Controller implements Person2Service {

    private Collection<Person> prosons = new ArrayList<>();

    public void save(@RequestBody Person person){
        System.out.println("Person2Service save ");
        prosons.add(person);
    }

    public Collection<Person> findAll(){
        System.out.println("Person2Service findAll ");
        return prosons;
    }
}
