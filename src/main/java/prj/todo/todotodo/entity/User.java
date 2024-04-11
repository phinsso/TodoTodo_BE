package prj.todo.todotodo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId; // 유저 아이디

    @Column
    private String userName; // 유저 이름

    @Column
    private String userPw; // 유저 패스워드
}
