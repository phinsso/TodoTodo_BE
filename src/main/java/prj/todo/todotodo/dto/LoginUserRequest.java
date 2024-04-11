package prj.todo.todotodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prj.todo.todotodo.entity.User;

@Getter
@AllArgsConstructor
public class LoginUserRequest {

    private String userId;
    private String userPw;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .build();
    }
}
