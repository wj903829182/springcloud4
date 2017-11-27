package com.jack.controller;

import com.jack.dao.PersonRepository;
import com.jack.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jack on 2017/11/27.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/save")
    public String savePerson() {
        Person person = new Person();
        person.setAge(18);
        person.setName("jack1");
        personRepository.save(person);
        return "添加成功";
    }
}
