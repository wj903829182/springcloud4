package com.jack.dao;

import com.jack.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 2017/11/27.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
