package com.student.management.payload.request;

import lombok.Data;

@Data
public class StudentRequest {
    Integer groupId;
    String firstName;
    String lastName;
}
