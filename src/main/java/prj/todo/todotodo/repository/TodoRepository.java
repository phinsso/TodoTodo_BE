package prj.todo.todotodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prj.todo.todotodo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
