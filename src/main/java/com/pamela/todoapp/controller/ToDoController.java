package com.pamela.todoapp.controller;

import com.pamela.todoapp.dto.ToDo;
import com.pamela.todoapp.request.CreateToDoRequest;
import com.pamela.todoapp.request.UpdateToDoRequest;
import com.pamela.todoapp.response.ToDoResponse;
import com.pamela.todoapp.service.ToDoService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@RestController
class ToDoController {

    private final ToDoService toDoService;

    @Operation(
            tags = "To-do operations",
            summary = "Endpoint for to-do creation",
            description = "Endpoint for to-do creation."
    )
    @PostMapping(path = "/to-do")
    public ResponseEntity<ToDoResponse> createToDo(Authentication authentication,
                                                   @RequestBody @Valid @NotNull CreateToDoRequest request) {

        final String userEmail = authentication.getName();

        final ToDo toDo = toDoService.createToDo(userEmail,
                new ToDo(request.getTitle(), request.getDescription()));

        return new ResponseEntity<>(
                new ToDoResponse(toDo.getId(), toDo.getTitle(), toDo.getContent()),
                HttpStatus.CREATED
        );
    }

    @Operation(
            tags = "To-do operations",
            summary = "Endpoint for fetching all to-do list.",
            description = "Endpoint for fetching all to-do list."
    )
    @GetMapping(path = "/all-to-do")
    public ResponseEntity<List<ToDoResponse>> getToDoList(Authentication authentication) {

        final String userEmail = authentication.getName();

        final List<ToDo> toDoList = toDoService.getToDoList(userEmail);

        return
                new ResponseEntity<>(
                        toDoList.stream()
                                .map(toDo -> new ToDoResponse(toDo.getId(), toDo.getTitle(), toDo.getContent()))
                                .collect(Collectors.toList()),
                        HttpStatus.OK);
    }

    @Operation(
            tags = "To-do operations",
            summary = "Endpoint for fetching to-do by id",
            description = "Endpoint for fetching to-do by id."
    )
    @GetMapping(path = "/to-do")
    public ResponseEntity<ToDoResponse> getToDo(@RequestParam Long toDoId) {

        final Optional<ToDo> toDoOpt = toDoService.getToDo(toDoId);

        return new ResponseEntity<>(
                toDoOpt.map(t -> new ToDoResponse(t.getId(), t.getTitle(), t.getContent()))
                        .orElse(new ToDoResponse()), HttpStatus.OK);
    }

    @Operation(
            tags = "To-do operations",
            summary = "Endpoint for deleting to-do by id",
            description = "Endpoint for deleting to-do by id."
    )
    @DeleteMapping("/to-do")
    public ResponseEntity deleteToDo(Authentication authentication, @RequestParam Long toDoId) {
        toDoService.delete(authentication.getName(), toDoId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Operation(
            tags = "To-do operations",
            summary = "Endpoint for updating to-do",
            description = "Endpoint for updating to-do."
    )
    @PutMapping("/to-do")
    public ResponseEntity<ToDoResponse> updateToDo(Authentication authentication,
                                                   @RequestBody @Valid @NotNull UpdateToDoRequest request
    ) {
        final Optional<ToDo> toDoOpt = toDoService.update(authentication.getName(), request.getId(),
                request.getTitle(), request.getDescription());

        return new ResponseEntity<>(
                toDoOpt.map(t -> new ToDoResponse(t.getId(), t.getTitle(), t.getContent()))
                        .orElse(new ToDoResponse()), HttpStatus.OK);
    }

}
