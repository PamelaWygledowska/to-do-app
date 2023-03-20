package com.pamela.todoapp.service;

import com.pamela.todoapp.dto.User;
import com.pamela.todoapp.entity.UserEntity;
import com.pamela.todoapp.exception.InvalidUserDataException;
import com.pamela.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createNewUser(User user) {
        log.info("Creating user {}", user.getEmail());
        validateNewUser(user);
        log.info("User {} successfully validated", user.getEmail());
        userRepository.save(map(user));
        log.info("User created {}", user.getEmail());
    }

    public Optional<User> getUser(String username) {
        return map(userRepository.findByEmail(username));
    }

    private UserEntity map(User user) {
        return new UserEntity(user.getEmail(), user.getPassword());
    }

    private Optional<User> map(Optional<UserEntity> userEntityOpt) {
        return userEntityOpt.map(user -> new User(user.getEmail(), user.getPassword()));
    }

    private void validateNewUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new InvalidUserDataException("User email already exists");
        }
    }
}
