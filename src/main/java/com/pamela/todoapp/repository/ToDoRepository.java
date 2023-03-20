package com.pamela.todoapp.repository;

import com.pamela.todoapp.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {

    //  JPQL QUERY
    @Query("SELECT t from ToDoEntity t WHERE t.user.email = :userEmail")
    List<ToDoEntity> findByUserEmail(@Param("userEmail") String userEmail);

    // NATIVE QUERY
    @Modifying
    @Query(value = "DELETE FROM TO_DO where id = :toDoId", nativeQuery = true)
    void deleteUserToDo(@Param("toDoId") Long toDoId);

//  I'm aware that for both above queries I could use Named Based Query Methods. Just done for practice.
}
