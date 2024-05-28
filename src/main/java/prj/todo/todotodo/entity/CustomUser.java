package prj.todo.todotodo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


@Getter
@Setter
// User 클래스: 스프링 시큐리티가 제공하는 UserDetails 인터페이스(사용자의 인증 및 권한 정보를 제공)의 구현체.
public class CustomUser extends User {

    private Member member;

    public CustomUser(Member member) {
        super(member.getUsername(), member.getPassword(), AuthorityUtils.NO_AUTHORITIES);
        this.member = member;
    }
}
