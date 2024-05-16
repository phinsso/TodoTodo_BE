package prj.todo.todotodo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {

    @Id
    private String userId;

    private String username;
    private String password;

    private String role;
}
