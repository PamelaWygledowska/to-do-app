package com.pamela.todoapp.controller;

import com.pamela.todoapp.dto.User;
import com.pamela.todoapp.request.CreateUserRequest;
import com.pamela.todoapp.response.CreateUserResponse;
import com.pamela.todoapp.service.UserService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
class UserController {

    private final UserService userService;

    @Operation(
            tags = "User operations",
            summary = "Create user")
    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@NotNull @Valid @RequestBody CreateUserRequest request) {
        userService.createNewUser(map(request));
        return new ResponseEntity<>(new CreateUserResponse(request.getEmail()), HttpStatus.CREATED);
    }

    private static User map(CreateUserRequest request) {
        return new User(request.getEmail(), request.getPassword());
    }

}
