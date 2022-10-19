package com.student.management.repo;

import com.student.management.model.Mark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkRepository extends CrudRepository<Mark, Integer> {
    List<Mark> findMarkByStudent_StudentId(Integer id);
}
