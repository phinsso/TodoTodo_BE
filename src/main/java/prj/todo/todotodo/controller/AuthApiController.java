package prj.todo.todotodo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prj.todo.todotodo.dto.RegisterMemberRequest;
import prj.todo.todotodo.entity.Member;
import prj.todo.todotodo.service.AuthService;

@RestController
@RequiredArgsConstructor // final 키워드가 붙은 필드의 생성자를 만들어줌
@Slf4j
public class AuthApiController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody RegisterMemberRequest request) {
        Member registered = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다");
    }

    @GetMapping("/todos/session-id")
    public String getSessionId(HttpSession session) {
        String sessionId = session.getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        log.debug("Current session ID: {}", sessionId);
        log.debug("Current session Name: {}", currentUserName);
        return "Current session ID: " + sessionId + "   Name: " + currentUserName;
    }
}
