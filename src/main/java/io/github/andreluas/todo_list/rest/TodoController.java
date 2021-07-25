package io.github.andreluas.todo_list.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.andreluas.todo_list.model.Todo;
import io.github.andreluas.todo_list.repository.TodoRepository;

@RestController // Faz com que a classe TodoController, seja um componente do Spring
// Scaneia e registra como componente rest, para que ele receba requisições e envie respostas
@RequestMapping("/api/todos") // Mapeia url para qual sera feito as requisições 
@CrossOrigin("*")
public class TodoController {
    
    @Autowired // Quando spring fizer mapeamento do Controller, faça injeção desta dependencia repository
    private TodoRepository repository;

    @PostMapping // Caso faça um post para /api/todos, cai dentro deste metódo
    public Todo save(@RequestBody Todo todo) { // Requestbody -> Faz com que o objeto seja alimentado quando um cliente fizer uma reposição post para url
        return repository.save(todo);
    }

    @GetMapping
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}") // retorna url/api/todos/{id}
    public Todo getById(@PathVariable Long id) { // PathVariable -> Faz com que id receba id passado na url
        return repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("{id}/done") // Faz atualização parcial para um recurso
    public Todo markAsDone(@PathVariable Long id) {
        return repository.findById(id).map(todo -> {
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            repository.save(todo);
            return todo;
        }).orElse(null);
    }
}