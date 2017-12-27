package com.jack.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jack.entity.Student;
import com.jack.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jack
 * @since 2017-12-27
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @RequestMapping(value = "findStudent",method = RequestMethod.GET)
    public JSONObject findStudent(){
        EntityWrapper<Student> entityWrapper = new EntityWrapper<>();
        //entityWrapper.eq("id",3);
        //entityWrapper.eq("name","jackmapper1");
        //List<Student> students = iStudentService.selectList(entityWrapper);
        List<Student> students = iStudentService.selectPage(new Page<Student>(1,2), entityWrapper).getRecords();
        JSONObject result  = new JSONObject();
        result.put("students",students);
        return result;
    }

    @RequestMapping(value = "addStudent",method = RequestMethod.GET)
    public JSONObject addStudent(){
        Student s = new Student();
        s.setName("jack1");
        s.setNote("this is mybatisplus test");
        s.setSex(1);
        JSONObject result  = new JSONObject();
        boolean flag = iStudentService.insert(s);
        result.put("插入结果",flag);
        return result;
    }

    @RequestMapping(value = "deleteStudent",method = RequestMethod.GET)
    public JSONObject deleteStudent(){
        Student s = new Student();
        s.setId(11);
        JSONObject result  = new JSONObject();
        boolean flag = iStudentService.deleteById(s.getId());
        result.put("删除结果",flag);
        return result;
    }

    @RequestMapping(value = "updateStudent",method = RequestMethod.GET)
    public JSONObject updateStudent(@RequestBody JSONObject data){
        Integer id = data.getInteger("id");
        Student s = new Student();
        s.setId(10);
        s.setNote("this is mybatis plus update");
        JSONObject result  = new JSONObject();
        boolean flag = iStudentService.updateById(s);
        result.put("更新结果",flag);
        return result;
    }

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public JSONObject test(Long id){
        System.out.println("id is: "+id);
        JSONObject result  = new JSONObject();
        /*boolean flag = iStudentService.updateById(s);*/
        result.put("更新结果",id);
        return result;
    }


}

