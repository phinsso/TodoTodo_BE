package prj.todo.todotodo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prj.todo.todotodo.dto.JoinUserRequestDTO;
import prj.todo.todotodo.dto.LoginUserRequestDTO;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.service.CustomUserDetailsService;
import prj.todo.todotodo.service.UserService;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<User> joinUser(@RequestBody JoinUserRequestDTO dto) {
        User joined = userService.joinProcess(dto);

        return (joined != null) ?
                ResponseEntity.status(HttpStatus.OK).body(joined) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 로그인
    /* @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody loginUserRequestDTO dto) {
        User loggedIn = userService.loginProcess(dto);

        return (loggedIn != null) ?
                ResponseEntity.status(HttpStatus.OK).body(loggedIn) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } */

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequestDTO dto, HttpSession session) {
        // 사용자 정보 가져오기
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUserId());

        // 사용자 검증
        if (userDetails != null && userDetails.getPassword().equals(dto.getPassword())) {
            // 사용자 정보 세션에 저장
            session.setAttribute("userDetails", userDetails);
            log.info("로그인 되었습니다");
            return ResponseEntity.ok(userDetails);
        } else {
            // 인증 실패 시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
