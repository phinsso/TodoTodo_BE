package prj.todo.todotodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.CreateTodoRequest;
import prj.todo.todotodo.entity.Category;
import prj.todo.todotodo.entity.Member;
import prj.todo.todotodo.entity.Todo;
import prj.todo.todotodo.exception.CategoryNotFoundException;
import prj.todo.todotodo.repository.CategoryRepository;
import prj.todo.todotodo.repository.MemberRepository;
import prj.todo.todotodo.repository.TodoRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;

    public Todo createTodo(CreateTodoRequest request, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 존재하지 않습니다."));

        // 특정 카테고리가 특정 사용자의 소유인지 확인
        // 카테고리의 ID가 요청된 카테고리 ID와 일치하는지, 카테고리의 소유자(member)의 ID가 현재 로그인한 사용자의 ID와 일치하는지
        Category category = categoryRepository.findByIdAndMemberId(request.getCategoryId(), member.getId())
                .orElseThrow(() -> new CategoryNotFoundException("해당하는 카테고리가 존재하지 않거나 사용자가 소유하지 않은 카테고리입니다."));

        Todo todo = Todo.builder()
                .member(member)
                .category(category)
                .title(request.getTitle())
                .dueDate(request.getDueDate())
                .isCompleted(false)
                .build();

        return todoRepository.save(todo);
    }
}
