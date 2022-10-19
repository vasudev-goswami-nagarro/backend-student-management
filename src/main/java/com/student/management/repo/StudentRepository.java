package com.student.management.repo;

import com.student.management.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findAllByGroup_GroupId(Integer id);

}
