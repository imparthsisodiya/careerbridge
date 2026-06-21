package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.ChangePasswordRequest;
import com.careerbridge.dto.UserResponse;
import com.careerbridge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{email}")
    public ApiResponse<UserResponse> getStudentByEmail(@PathVariable String email) {
        return new ApiResponse<>(true, "Student fetched",
                studentService.getStudentByEmail(email));
    }

    @PutMapping("/change-password")
    public ApiResponse<String> changePassword(@RequestBody ChangePasswordRequest request) {
        studentService.changePassword(request);
        return new ApiResponse<>(true, "Password changed successfully", "Updated");
    }
}
