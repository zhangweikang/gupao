package my.project.cloud.feign.api.service;

import my.project.cloud.feign.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * Person api 接口
 * {@link Person}
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
@FeignClient(value = "spring-cloud-feign-provider")
public interface Person2Service {

    @PostMapping("/lb/person/save")
    void save(@RequestBody Person person);

    @GetMapping("/lb/person/find/all")
    Collection<Person> findAll();
}
