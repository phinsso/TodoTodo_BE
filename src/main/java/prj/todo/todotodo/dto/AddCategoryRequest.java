package prj.todo.todotodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prj.todo.todotodo.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddCategoryRequest {
    private String name;

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }
}
