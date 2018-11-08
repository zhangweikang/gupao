package my.project.cloud.feign.domain;

/**
 * 人模型实例
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
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
