package my.project.cloud.feign.api.service;

import my.project.cloud.feign.api.hystrix.PersonServiceFallBack;
import my.project.cloud.feign.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * Person api 接口
 * {@link my.project.cloud.feign.domain.Person}
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
@FeignClient(value = "spring-cloud-feign-provider",fallback = PersonServiceFallBack.class)
public interface PersonService {

    @PostMapping("/person/save")
    void save(@RequestBody Person person);

    @GetMapping("/person/find/all")
    Collection<Person> findAll();
}
