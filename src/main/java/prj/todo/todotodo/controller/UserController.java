package prj.todo.todotodo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prj.todo.todotodo.dto.AddUserRequest;
import prj.todo.todotodo.dto.LoginUserRequest;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.service.UserService;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService; // user 서비스 객체 주입

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody AddUserRequest request) {
        User created = userService.joinUser(request);

        return (created != null) ? // 유저 생성이 되면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginUserRequest request) {
        User logged = userService.loginUser(request);

//        return (logged != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(logged) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if (logged != null) {
            log.info("User logged in successfully: {}", logged.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(logged);
        } else {
            log.warn("Login failed for user: {}", request.getUserId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
