package com.jack.service;

import com.jack.entity.Student;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2017-12-27
 */
public interface IStudentService extends IService<Student> {
    Student findStudentById(Integer id);
    Student findStudentById2(Integer id);
}
