package my.project.spring.boot.mvc.domain;

/**
 * 实体
 *
 * @author ZhangWeiKang
 * @create 2018/7/23
 */
public class Person {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
