package com.student.management.controller;

import com.student.management.payload.request.TeacherRequest;
import com.student.management.service.StudentService;
import com.student.management.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    @Operation(summary = "Get number of students for a particular teacher id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Number of students for a particular teacher id")
    })
    public ResponseEntity<Object> getNumberOfStudentsForTeacherId(@PathVariable("id") int id) {
        Object o = studentService.getStudentsByTeacherId(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @Operation(summary = "Add teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add teacher")
    })
    @PostMapping
    public ResponseEntity<Object> addTeacher(@RequestBody TeacherRequest teacherRequest) {
        Object o = teacherService.addTeacher(teacherRequest);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete teacher")
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteTeacher(Integer id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Operation(summary = "Return list of all teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All teachers returned")
    })

    @GetMapping
    public ResponseEntity<Object> findAllTeacher() {
        Object o = teacherService.findAllTeacher();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
