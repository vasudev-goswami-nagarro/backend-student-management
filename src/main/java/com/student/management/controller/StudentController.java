package com.student.management.controller;

import com.student.management.payload.request.StudentRequest;
import com.student.management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    @Operation(summary = "Get mark for a particular student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Mark for a particular student id returned")
    })
    public ResponseEntity<Object> getMarksForStudent(@PathVariable("id") int id) {
        Object o = studentService.getMarkByStudentId(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @Operation(summary = "Add student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add student")
    })
    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody StudentRequest studentRequest) {
        Object o = studentService.addStudent(studentRequest);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete student")
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteStudent(Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Operation(summary = "Return list of all student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All students returned")
    })
    @GetMapping
    public ResponseEntity<Object> findAllStudent() {
        Object o = studentService.findAllStudent();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
