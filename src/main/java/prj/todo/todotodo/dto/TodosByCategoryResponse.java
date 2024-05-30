package prj.todo.todotodo.dto;

import lombok.Getter;
import prj.todo.todotodo.entity.Category;

import java.util.List;

@Getter
public class TodosByCategoryResponse {
    private Long id;
    private String name;
    private List<TodoResponse> todos;

    public TodosByCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.todos = category.getTodos().stream().map(TodoResponse::new).toList();
    }
}
