package com.student.management.model;

import lombok.*;

import javax.persistence.*;
@Data
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    String firstName;
    String lastName;
}
