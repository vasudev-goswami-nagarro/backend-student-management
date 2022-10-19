package com.student.management.controller;

import com.student.management.model.Subject;
import com.student.management.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Operation(summary = "Add subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add subject")
    })
    @PostMapping
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject) {
        Object o = subjectService.addSubject(subject);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete subject")
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteSubject(Integer id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Operation(summary = "Return list of all subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All subjects returned")
    })

    @GetMapping
    public ResponseEntity<Object> findAllSubject() {
        Object o = subjectService.findAllSubject();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
