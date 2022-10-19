package com.student.management.repo;

import com.student.management.model.Group;
import com.student.management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
