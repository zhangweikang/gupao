package my.project.cloud.feign.consumer.controller;

import my.project.cloud.feign.api.service.Person2Service;
import my.project.cloud.feign.api.service.PersonService;
import my.project.cloud.feign.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Person Controller
 *
 * {@link my.project.cloud.feign.api.service.PersonService}
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
@RestController
public class SpringCloudFeignConsumerController {

    @Autowired
    private PersonService personService;
    @Autowired
    private Person2Service person2Service;

    @PostMapping("/person/save")
    public void save(@RequestBody Person person){
        personService.save(person);
    }

    @GetMapping("/person/find/all")
    public Collection<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping("/lb/person/save")
    public void loadBalancerSave(@RequestBody Person person){
        personService.save(person);
    }

    @GetMapping("/lb/person/find/all")
    public Collection<Person> loadBalancerFindAll(){
        return personService.findAll();
    }
}
