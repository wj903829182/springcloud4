package com.jack.service.impl;

import com.jack.entity.Student;
import com.jack.mapper.StudentMapper;
import com.jack.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2017-12-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
