package com.student.management.service;

import com.student.management.exceptions.CustomException;
import com.student.management.model.Teacher;
import com.student.management.payload.request.TeacherRequest;
import com.student.management.repo.GroupRepository;
import com.student.management.repo.SubjectRepository;
import com.student.management.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Object addTeacher(TeacherRequest teacherRequest) {
        return groupRepository.findById(teacherRequest.getGroupId()).map(group -> {
            subjectRepository.findById(teacherRequest.getSubjectId()).map(subject -> {
                Teacher student = Teacher.builder()
                        .group(group)
                        .subject(subject)
                        .build();
                return teacherRepository.save(student);
            }).orElseThrow(() -> {
                throw new CustomException(HttpStatus.NOT_FOUND,
                        "Subject with id " + teacherRequest.getGroupId() + " not found");
            });
            return "";
        }).orElseThrow(() -> {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Group with id " + teacherRequest.getGroupId() + " not found");
        });
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    public Object findAllTeacher() {
        return teacherRepository.findAll();
    }

}
