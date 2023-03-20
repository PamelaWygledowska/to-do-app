package com.pamela.todoapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserResponse {
    private final String email;
}
