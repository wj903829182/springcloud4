package com.jack.mapper;

import com.jack.entity.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jack
 * @since 2017-12-27
 */
public interface StudentMapper extends BaseMapper<Student> {
    Student findStudentById(Integer id);
    @Select("select * from Student s where s.id = #{id}")
    Student findStudentById2(@Param("id") Integer id);
}
