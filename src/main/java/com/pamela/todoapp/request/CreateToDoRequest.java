package com.pamela.todoapp.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CreateToDoRequest {

    @Schema(
            example = "Example title",
            maxLength = 50)
    @NotEmpty
    @Size(max = 50)
    private String title;

    @Schema(
            example = "Example description",
            maxLength = 500)
    @Size(max = 500)
    private String description;

}
