package my.demo.service;

import my.spring.annotation.Service;

/**
 * @author ZhangWeiKang
 * @create 2018/7/24
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Override
    public String query(String name) {
        System.out.println("name = " + name);
        return name;
    }
}
