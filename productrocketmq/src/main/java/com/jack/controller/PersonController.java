package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.dao.PersonRepository;
import com.jack.entity.Person;
import com.jack.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/11/27.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    EntityManager entityManager;


    @RequestMapping("/save")
    public String savePerson() {
        Person person = new Person();
        person.setAge(18);
        person.setName("jack1");
        personRepository.save(person);
        return "添加成功";
    }

    @RequestMapping("/delete")
    public String deletePerson(Integer id) {
        if (id == null) {
            return "参数错误";
        }
        personRepository.delete(id);
        return "删除成功";
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping("/update")
    public String updatePerson() {
        /*Person person = new Person();
        person.setId(2);
        person.setAge(18);
        person.setName("jack3");
        personRepository.save(person);*/
        personRepository.updatePerson("jack3");
        if (1 == 1) {
            throw  new RuntimeException("主动抛异常");
        }
        return "更新成功";

    }

    @RequestMapping("/find")
    public JSONObject findPerson(Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) {
            jsonObject.put("data", "查找失败");
            return jsonObject;
        }
        Person person = personRepository.findOne(id);
        jsonObject.put("data",person);
        return jsonObject;
    }

    @RequestMapping("/findAll")
    public JSONObject findAllPerson(Integer age) {
        JSONObject jsonObject = new JSONObject();
        /*List<PersonVO> person = personRepository.selectAllPerson(age);
        jsonObject.put("data",person);*/
       Query query = entityManager.createNativeQuery("Person.all");
        List list = query.getResultList();
        jsonObject.put("data",list);
        return jsonObject;
    }


}
