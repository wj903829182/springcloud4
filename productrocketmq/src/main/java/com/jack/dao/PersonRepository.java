package com.jack.dao;

import com.jack.entity.Person;
import com.jack.vo.PersonVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jack on 2017/11/27.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query(value = "update person p set p.age = 20 where p.name = ?1",nativeQuery = true)
    @Modifying
    int updatePerson(/*@Param("name")*/ String name);

    //@Query(value = "select id,age,name from person p where p.age > ?1",nativeQuery = true)
    @Query(value = "select new com.jack.vo.PersonVO(p.id,p.age,p.name) from Person p where p.age > ?1"/*,nativeQuery = true*/)
    List<PersonVO> selectAllPerson(Integer age);

}
