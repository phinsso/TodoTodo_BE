package prj.todo.todotodo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id; // 할 일 기본키

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String title; // 할 일 제목

    @Column(name = "due_date")
    private LocalDate dueDate; // 마감일

    @Column(name = "is_completed")
    private boolean isCompleted; // 할 일의 완료 여부

}
