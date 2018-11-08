package my.project.cloud.feign.provider.controller;

import my.project.cloud.feign.api.service.PersonService;
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
public class SpringCloudFeignProviderController implements PersonService {

    private Collection<Person> prosons = new ArrayList<>();

    public void save(@RequestBody Person person){
        System.out.println("PersonService save ");
        prosons.add(person);
    }

    public Collection<Person> findAll(){
        System.out.println("PersonService findAll ");
        return prosons;
    }
}
