package com.student.management.controller;

import com.student.management.payload.request.MarkRequest;
import com.student.management.service.MarkService;
import com.student.management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("mark")
public class MarkController {

    @Autowired
    MarkService markService;
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    @Operation(summary = "Get list of marks in each subject for a particular student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of marks in each subject for a particular student id")
    })
    public ResponseEntity<Object> getListOfMarksInEachSubjectForStudentId(@PathVariable("id") int id) {
        Object o = studentService.getListOfMarksInEachSubjectByStudentId(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @Operation(summary = "Add mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add mark")
    })
    @PostMapping
    public ResponseEntity<Object> addMark(@RequestBody MarkRequest markRequest) {
        Object o = markService.addMark(markRequest);
        return new ResponseEntity<>(o, HttpStatus.CREATED);

    }

    @Operation(summary = "Delete mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete mark")
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteMark(Integer id) {
        markService.deleteMark(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);

    }

    @Operation(summary = "Return list of all mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All marks returned")
    })

    @GetMapping
    public ResponseEntity<Object> findAllMark() {
        Object o = markService.findAllMark();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
