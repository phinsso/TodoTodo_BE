package prj.todo.todotodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prj.todo.todotodo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameAndMemberUsername(String name, String username);
}
