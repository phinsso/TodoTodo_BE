package prj.todo.todotodo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import prj.todo.todotodo.dto.CategoryResponse;
import prj.todo.todotodo.dto.CreateCategoryRequest;
import prj.todo.todotodo.entity.Category;
import prj.todo.todotodo.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/todo/categories")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Category created = categoryService.createCategory(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body("카테고리가 추가되었습니다.");
    }

    @GetMapping("/todo/categories")
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<CategoryResponse> categories = categoryService.findAllCategories(username);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
