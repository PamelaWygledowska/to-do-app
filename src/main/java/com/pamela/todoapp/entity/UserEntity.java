package com.pamela.todoapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER_TABLE")
public class UserEntity {

    @Id
    @NotEmpty
    @Size(max = 50)
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ToDoEntity> toDoList;

    public UserEntity(String email) {
        this.email = email;
    }

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
