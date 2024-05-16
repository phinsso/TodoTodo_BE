package prj.todo.todotodo.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinUserRequestDTO {
    private String userId;
    private String username;
    private String password;
}

