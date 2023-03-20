package com.pamela.todoapp.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateUserRequest {

    @Schema(
            example = "pamele@wygledowska.com")
    @NotEmpty
    @Email
    private final String email;

    @Schema(
            example = "pass")
    @NotEmpty
    @Size(min = 4, max = 50)
    private final String password;
}
