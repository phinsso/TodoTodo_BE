package prj.todo.todotodo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class UpdateTodoRequest {
    private String title;
    private LocalDate dueDate;

    @JsonProperty("isCompleted")
    private boolean isCompleted;
}
