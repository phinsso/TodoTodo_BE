package prj.todo.todotodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prj.todo.todotodo.dto.AddUserRequest;
import prj.todo.todotodo.entity.User;
import prj.todo.todotodo.service.UserService;

@RestController
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

}
