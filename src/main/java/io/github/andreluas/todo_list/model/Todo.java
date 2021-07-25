package io.github.andreluas.todo_list.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Entity // Criação da entidade
@Getter
@Setter // Getter and Setter - gerado automaticamente pelo Lombok
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usado strategy para auto incremento do ID
    private Long id;

    @Column // Criação de coluna no H2
    private String description;

    @Column
    private boolean done;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdDate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime doneDate;

    @PrePersist 
    public void beforeSave() {
        setCreatedDate(LocalDateTime.now());
    }
}
