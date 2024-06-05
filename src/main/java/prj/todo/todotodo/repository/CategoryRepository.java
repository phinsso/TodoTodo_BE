package prj.todo.todotodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prj.todo.todotodo.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameAndMemberUsername(String name, String username);

    Optional<Category> findByIdAndMemberId(Long categoryId, Long memberId);

    List<Category> findAllByMemberId(Long id);

    // fetch join을 사용하여 Category와 해당 카테고리의 Todo를 한번에 로드
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.todos WHERE c.member.id = :memberId")
    List<Category> findAllWithTodosByMemberId(@Param("memberId") Long memberId);
}
