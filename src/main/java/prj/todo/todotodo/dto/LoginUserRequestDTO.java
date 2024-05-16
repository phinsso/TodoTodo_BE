package prj.todo.todotodo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserRequestDTO {
    private String userId;
    private String password;
}
