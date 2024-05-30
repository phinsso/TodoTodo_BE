package prj.todo.todotodo.dto;

import lombok.Getter;
import prj.todo.todotodo.entity.Todo;

import java.time.LocalDate;

@Getter
public class TodoResponse {
    private Long id;
    private boolean isCompleted;
    private String title;
    private LocalDate dueDate;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.isCompleted = todo.isCompleted();
        this.title = todo.getTitle();
        this.dueDate = todo.getDueDate();
    }
}
