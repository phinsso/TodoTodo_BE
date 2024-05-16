package prj.todo.todotodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.JoinUserRequestDTO;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User joinProcess(JoinUserRequestDTO dto) {

        // db에 이미 동일한 userId를 가진 유저가 존재하는지 검사
        boolean isUser = userRepository.existsByUserId(dto.getUserId());
        if(isUser) {
            return null;
        }

        User data = new User();

        data.setUserId(dto.getUserId());
        data.setUsername(dto.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        data.setRole("ROLE_USER");

        return userRepository.save(data);

    }
}
