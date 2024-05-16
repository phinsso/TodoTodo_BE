package prj.todo.todotodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.CustomUserDetails;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 해당 userId를 가진 유저의 정보를 조회해서 UserDetails 타입으로 리턴
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User userData = userRepository.findByUserId(userId);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
