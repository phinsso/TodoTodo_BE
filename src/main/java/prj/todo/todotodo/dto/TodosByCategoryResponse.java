package prj.todo.todotodo.dto;

import lombok.Getter;
import prj.todo.todotodo.entity.Category;

import java.util.List;

@Getter
public class TodosByCategoryResponse {
    private String name;
    private List<TodoResponse> todos;

    public TodosByCategoryResponse(Category category) {
        this.name = category.getName();
        this.todos = category.getTodos().stream().map(TodoResponse::new).toList();
    }
}
