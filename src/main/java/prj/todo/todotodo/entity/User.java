package prj.todo.todotodo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    private String userId; // 유저 아이디

    @Column
    private String userName; // 유저 이름

    @Column
    private String userPw; // 유저 패스워드

    // 회원가입에 필요한 생성자
    @Builder
    public User(String userId, String userPw, String userName) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
    }

    // 로그인에 필요한 생성자
    @Builder
    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
}
