package com.pamela.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDo {

    private long id;

    private String title;

    private String content;

    public ToDo(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
