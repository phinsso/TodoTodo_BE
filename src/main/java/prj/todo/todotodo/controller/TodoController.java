package prj.todo.todotodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prj.todo.todotodo.dto.CreateTodoRequest;
import prj.todo.todotodo.dto.TodosByCategoryResponse;
import prj.todo.todotodo.entity.Todo;
import prj.todo.todotodo.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo/todos")
    public ResponseEntity<String> createTodo(@RequestBody CreateTodoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Todo created = todoService.createTodo(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body("투두가 추가되었습니다.");
    }

    @GetMapping("/todo/todos")
    public ResponseEntity<List<TodosByCategoryResponse>> getTodosByCategory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<TodosByCategoryResponse> todos = todoService.getTodosByCategory(username);

        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }
}
