package com.student.management.service;

import com.student.management.exceptions.CustomException;
import com.student.management.model.Mark;
import com.student.management.payload.request.MarkRequest;
import com.student.management.repo.MarkRepository;
import com.student.management.repo.StudentRepository;
import com.student.management.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MarkService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Object addMark(MarkRequest markRequest) {
        return studentRepository.findById(markRequest.getStudentId()).map(student -> {
            subjectRepository.findById(markRequest.getSubjectId()).map(subject -> {
                Mark mark = Mark.builder()
                        .student(student)
                        .subject(subject)
                        .mark(markRequest.getMark())
                        .date(LocalDateTime.now())
                        .build();
                return markRepository.save(mark);
            }).orElseThrow(() -> {
                throw new CustomException(HttpStatus.NOT_FOUND,
                        "Subject with id " + markRequest.getSubjectId() + " not found");
            });
            return "";
        }).orElseThrow(() -> {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Student with id " + markRequest.getStudentId() + " not found");
        });
    }

    public void deleteMark(Integer id) {
        markRepository.deleteById(id);
    }

    public Object findAllMark() {
        return markRepository.findAll();
    }

}
