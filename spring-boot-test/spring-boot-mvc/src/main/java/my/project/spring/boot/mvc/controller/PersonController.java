package my.project.spring.boot.mvc.controller;

import my.project.spring.boot.mvc.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author ZhangWeiKang
 * @create 2018/7/23
 */
@RestController
public class PersonController {

    @PostMapping(value = "/get")
    public String get(){

        /*Person person = new Person();
        person.setId(1L);
        person.setName("zhangweikang");*/
        return "Hello World";
    }

    @PostMapping(value = "/proptioes2Json",
    consumes = "application/properties+person",
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person proptioes2Json(@RequestBody Person person){
        return person;
    }

    @PostMapping(value = "/json2proptioes",
            produces = "application/properties+person")
    public Person json2proptioes(@RequestBody Person person){
        return person;
    }
}
