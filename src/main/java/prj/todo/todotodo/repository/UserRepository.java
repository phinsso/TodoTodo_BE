package prj.todo.todotodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prj.todo.todotodo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 로그인시 사용되는 메서드
    User findByUserIdAndUserPw(String userId, String userPw);
}
