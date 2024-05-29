package prj.todo.todotodo.dto;

import lombok.Getter;
import prj.todo.todotodo.entity.Category;

import java.time.LocalDate;

@Getter
public class CreateTodoRequest {
    private Long categoryId;
    private String title;
    private LocalDate dueDate;
}
