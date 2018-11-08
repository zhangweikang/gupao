package my.project.boot.domain;

import java.io.Serializable;

public class User implements Serializable {
    //串行版本ID
    private static final long serialVersionUID = -9039416343187099987L;

    private String id;

    private String realName;

    private Integer myAge;

    private String myAddres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getMyAge() {
        return myAge;
    }

    public void setMyAge(Integer myAge) {
        this.myAge = myAge;
    }

    public String getMyAddres() {
        return myAddres;
    }

    public void setMyAddres(String myAddres) {
        this.myAddres = myAddres;
    }
}