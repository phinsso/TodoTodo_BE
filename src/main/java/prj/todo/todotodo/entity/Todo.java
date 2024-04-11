package prj.todo.todotodo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId; // 투두 기본키

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column
    private String title; // 투두 이름

    @Column
    private LocalDate dueDate; // 마감일

    @Column
    private boolean completed; // 완료 여부

}
