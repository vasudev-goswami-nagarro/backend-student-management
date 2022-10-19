package com.student.management.service;

import com.student.management.model.Group;
import com.student.management.payload.request.GroupRequest;
import com.student.management.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public Object addGroup(GroupRequest groupRequest) {
        Group group = Group.builder().name(groupRequest.getName()).build();
        return groupRepository.save(group);
    }

    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    public Object findAllGroups() {
        return groupRepository.findAll();
    }
}
