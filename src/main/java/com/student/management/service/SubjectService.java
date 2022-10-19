package com.student.management.service;

import com.student.management.model.Subject;
import com.student.management.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Object addSubject(Subject subject) {
        return subjectRepository.save(subject);

    }

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

    public Object findAllSubject() {
        return subjectRepository.findAll();
    }
}
