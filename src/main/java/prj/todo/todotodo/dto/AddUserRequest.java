package prj.todo.todotodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prj.todo.todotodo.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddUserRequest {

    private String userId;
    private String userPw;
    private String userName;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .build();
    }
}
