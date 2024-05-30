package prj.todo.todotodo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // 카테고리 기본키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, unique = true)
    private String name; // 카테고리 이름 (종류)

    // 관계의 주인: Todo 엔티티의 category 필드
    // Category를 삭제할 때 이와 연관된 Todo도 함께 삭제
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos;

    @Builder
    public Category(Member member, String name) {
        this.member = member;
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
