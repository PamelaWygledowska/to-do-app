package com.pamela.todoapp.service;

import com.pamela.todoapp.dto.ToDo;
import com.pamela.todoapp.entity.ToDoEntity;
import com.pamela.todoapp.entity.UserEntity;
import com.pamela.todoapp.repository.ToDoRepository;
import com.pamela.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    private final UserRepository userRepository;

    public ToDo createToDo(String userEmail, ToDo toDo) {
        log.info("Creating to-do for user {}", userEmail);
        ToDoEntity toDoEntity = toDoRepository.save(map(userEmail, toDo));
        log.info("Created to-do for user {}", userEmail);
        return map(toDoEntity);
    }

    public List<ToDo> getToDoList(String userEmail) {
        return toDoRepository
                .findByUserEmail(userEmail)
                .stream()
                .map(toDoEntity -> map(toDoEntity))
                .collect(Collectors.toList());
    }

    public Optional<ToDo> getToDo(Long toDoId) {
        return
                toDoRepository
                        .findById(toDoId)
                        .map(toDoEntity -> map(toDoEntity));
    }

    @Transactional
    public void delete(String userEmail, Long toDoId) {

        UserEntity user = userRepository.findByEmail(userEmail).get();

        final Optional<ToDoEntity> toDoEntity = user.getToDoList().stream()
                .filter(t -> t.getId().equals(toDoId)).findAny();

        if (toDoEntity.isPresent()) {
            toDoRepository.deleteUserToDo(toDoId);
        } else {
            log.info("User {} tried to remove not his to do with id {}", userEmail, toDoId);
        }
    }

    @Transactional
    public Optional<ToDo> update(String userEmail, Long toDoId, String title,
                                 String description) {

        UserEntity user = userRepository.findByEmail(userEmail).get();

        final Optional<ToDoEntity> toDoEntity = user.getToDoList().stream()
                .filter(t -> t.getId().equals(toDoId)).findAny();

        if (toDoEntity.isPresent()) {
            ToDoEntity toDoForUpdate = toDoRepository.findById(toDoId).get();
            toDoForUpdate.setTitle(title);
            toDoForUpdate.setDescription(description);
            return Optional.of(map(toDoForUpdate));
        } else {
            log.info("User {} tried to update not his to do with id {}", userEmail, toDoId);
            return Optional.empty();
        }
    }

    private ToDo map(ToDoEntity toDoEntity) {
        return new ToDo(toDoEntity.getId(), toDoEntity.getTitle(), toDoEntity.getDescription());
    }

    private ToDoEntity map(String userEmail, ToDo toDo) {
        return new ToDoEntity(null, toDo.getTitle(), toDo.getContent(), new UserEntity(userEmail));
    }
}
