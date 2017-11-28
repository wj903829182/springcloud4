package com.jack.vo;

/**
 * Created by jack on 2017/11/27.
 */
public class PersonVO {
    private Integer id;
    private Integer age;
    private String name;

    public PersonVO() {
    }

    public PersonVO(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
