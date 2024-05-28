package prj.todo.todotodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.entity.CustomUser;
import prj.todo.todotodo.entity.Member;
import prj.todo.todotodo.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
// UserDetailsService: 스프링 시큐리티에서 제공하는 사용자의 정보를 가져오는 인터페이스
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 사용자의 정보를 불러와 UserDetails 타입으로 return
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUsername(username);

        return member.map(CustomUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + ": 해당 유저를 찾을 수 없습니다"));
    }
}
