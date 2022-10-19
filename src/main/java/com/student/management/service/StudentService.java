package com.student.management.service;

import com.student.management.exceptions.CustomException;
import com.student.management.model.Mark;
import com.student.management.model.Student;
import com.student.management.payload.request.StudentRequest;
import com.student.management.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarkRepository markRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Object addStudent(StudentRequest studentRequest) {
        return groupRepository.findById(studentRequest.getGroupId()).map(group -> {
            Student student = Student.builder()
                    .group(group)
                    .firstName(studentRequest.getFirstName())
                    .lastName(studentRequest.getLastName())
                    .build();
            return studentRepository.save(student);
        }).orElseThrow(() -> {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Teacher with id " + studentRequest.getGroupId() + " not found");
        });
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Object findAllStudent() {
        return studentRepository.findAll();
    }

    public Object getMarkByStudentId(int id) {
        return markRepository.findMarkByStudent_StudentId(id);
    }

    public Object getStudentsByTeacherId(int teacherId) {
        return teacherRepository.findById(teacherId).map(teacher1 -> {
            Integer groupId = teacher1.getGroup().getGroupId();
            return studentRepository.findAllByGroup_GroupId(groupId);
        }).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Teacher with id "+ teacherId +" not found"));
    }

    public Object getListOfMarksInEachSubjectByStudentId(int teacherId) {
        List<Mark> marks = markRepository.findMarkByStudent_StudentId(teacherId);
        Map<String,Integer> marksInSubject = new HashMap<>();
        marks.forEach(mark -> {
            subjectRepository.findById(mark.getSubject().getSubjectId()).ifPresent(subject -> {
                marksInSubject.put(subject.getTitle(), mark.getMark());
            });
        });
        return marksInSubject;
    }
}
