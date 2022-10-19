package com.student.management.controller;

import com.student.management.payload.response.JwtResponse;
import com.student.management.security.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JwtController {
    @Autowired
    JwtUtil jwtUtil;

    @Operation(summary = "Return a JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "New JWT returned")
    })
    @GetMapping("/jwt")
    public ResponseEntity<?> authenticateUser() {
        final String jwt = jwtUtil.generateToken("secureString");
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
