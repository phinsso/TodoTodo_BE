package prj.todo.todotodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.RegisterMemberRequest;
import prj.todo.todotodo.entity.Member;
import prj.todo.todotodo.exception.RegistrationException;
import prj.todo.todotodo.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member register(RegisterMemberRequest request) {
        Optional<Member> existingMember = memberRepository.findByUsername(request.getUsername());
        if (existingMember.isPresent()) {
            throw new RegistrationException("동일한 id를 가진 유저가 존재합니다.");
        }

        Member registerMember = new Member(
                request.getUsername(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                request.getNickname(),
                LocalDate.now()
        );

        return memberRepository.save(registerMember);
    }
}
