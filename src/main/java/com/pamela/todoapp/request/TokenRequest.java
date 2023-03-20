package com.pamela.todoapp.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRequest {

    @Schema(
            example = "pamele@wygledowska.com")
    private String email;

    @Schema(
            example = "pass")
    private String password;
}
