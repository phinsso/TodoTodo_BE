package prj.todo.todotodo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "registered_at")
    private LocalDate registeredAt;

    // 회원가입 시 사용되는 생성자 (id 제외)
    public Member(String username, String password, String nickname, LocalDate registeredAt) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.registeredAt = registeredAt;
    }
}
