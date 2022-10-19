package com.student.management.payload.request;

import lombok.Data;

@Data
public class MarkRequest {
    Integer studentId;

    Integer subjectId;

    Integer mark;

}
