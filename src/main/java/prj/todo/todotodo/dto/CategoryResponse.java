package prj.todo.todotodo.dto;

import lombok.Getter;
import prj.todo.todotodo.entity.Category;

@Getter
public class CategoryResponse {
    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
