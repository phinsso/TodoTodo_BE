package prj.todo.todotodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.AddUserRequest;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // user 리파지토리 객체 주입

    // 유저 단일 조회 (user 테이블에 해당 id를 가진 데이터가 있는지)
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // 유저 생성 (회원가입)
    public User joinUser(AddUserRequest request) {
        if (getUserById(request.getUserId()) != null) { // 테이블에 이미 존재하는 id일 경우 null 반환
            return null;
        }

        User user = request.toEntity();
        return userRepository.save(user);
    }



}
