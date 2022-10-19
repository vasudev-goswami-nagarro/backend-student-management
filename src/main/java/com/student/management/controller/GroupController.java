package com.student.management.controller;


import com.student.management.model.Group;
import com.student.management.payload.request.GroupRequest;
import com.student.management.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Operation(summary = "Add mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add mark")
    })
    @PostMapping
    public ResponseEntity<Object> addGroup(@RequestBody GroupRequest groupRequest) {
        Object o = groupService.addGroup(groupRequest);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete mark")
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteGroup(Integer id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Operation(summary = "Return list of all mark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All marks returned")
    })

    @GetMapping
    public ResponseEntity<Object> findAllGroup() {
        Object o = groupService.findAllGroups();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
