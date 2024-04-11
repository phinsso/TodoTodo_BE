package prj.todo.todotodo.entity;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId; // 카테고리 기본키

    @Column
    private String name; // 카테고리 이름 (종류)
}
