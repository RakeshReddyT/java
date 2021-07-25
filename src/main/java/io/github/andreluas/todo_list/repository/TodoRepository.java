package io.github.andreluas.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.andreluas.todo_list.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}