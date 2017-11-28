package com.jack.entity;

import com.jack.vo.PersonVO;

import javax.persistence.*;

/**
 * Created by jack on 2017/11/27.
 */
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Personall",
                query = "select id,age,name from person p where p.age > ?1",
                resultClass = PersonVO.class
        )
})
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer age;
    private String name;

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
