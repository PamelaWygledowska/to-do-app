package com.pamela.todoapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoResponse {

    private Long id;

    private String title;

    private String description;
}
