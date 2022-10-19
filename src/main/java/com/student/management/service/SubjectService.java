package com.student.management.service;

import com.student.management.model.Subject;
import com.student.management.payload.request.SubjectRequest;
import com.student.management.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Object addSubject(SubjectRequest subjectRequest) {
        Subject subject = Subject.builder()
                .title(subjectRequest.getTitle()).build();
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

    public Object findAllSubject() {
        return subjectRepository.findAll();
    }
}
