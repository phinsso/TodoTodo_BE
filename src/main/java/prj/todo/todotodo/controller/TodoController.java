package prj.todo.todotodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import prj.todo.todotodo.dto.CreateTodoRequest;
import prj.todo.todotodo.dto.TodosByCategoryResponse;
import prj.todo.todotodo.dto.UpdateTodoRequest;
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
        todoService.createTodo(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body("투두가 추가되었습니다.");
    }

    @GetMapping("/todo/todos")
    public ResponseEntity<List<TodosByCategoryResponse>> getTodosByCategory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<TodosByCategoryResponse> todos = todoService.getTodosByCategory(username);

        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PatchMapping("/todo/todos/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") Long id, @RequestBody UpdateTodoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        todoService.updateTodo(id, request, username);

        return ResponseEntity.status(HttpStatus.OK).body("투두가 수정되었습니다.");
    }

    @DeleteMapping("/todo/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String username = authentication.getName();
        todoService.deleteTodo(id, username);

        return ResponseEntity.status(HttpStatus.OK).body("투두가 삭제되었습니다.");
    }
}
