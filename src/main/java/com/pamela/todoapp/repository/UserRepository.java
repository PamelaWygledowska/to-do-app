package com.pamela.todoapp.repository;

import com.pamela.todoapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    // NAME-BASED QUERY
    Optional<UserEntity> findByEmail(String email);

    // NAME-BASED QUERY
    boolean existsByEmail(String email);
}
