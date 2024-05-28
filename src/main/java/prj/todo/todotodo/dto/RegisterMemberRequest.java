package prj.todo.todotodo.dto;

import lombok.Getter;

@Getter
public class RegisterMemberRequest {
    private String username;
    private String password;
    private String nickname;
}
