package prj.todo.todotodo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import prj.todo.todotodo.common.ControllerUtil;
import prj.todo.todotodo.dto.CategoryResponse;
import prj.todo.todotodo.dto.CreateCategoryRequest;
import prj.todo.todotodo.dto.UpdateCategoryRequest;
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
        String username = ControllerUtil.getAuthenticatedUsername();
        categoryService.createCategory(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body("카테고리가 추가되었습니다.");
    }

    @GetMapping("/todo/categories")
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        String username = ControllerUtil.getAuthenticatedUsername();
        List<CategoryResponse> categories = categoryService.findAllCategories(username);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PatchMapping("/todo/categories/{id}")
    public ResponseEntity<String> editCategory(@PathVariable("id") Long id, @RequestBody UpdateCategoryRequest request) {
        String username = ControllerUtil.getAuthenticatedUsername();
        categoryService.updateCategory(id, request, username);

        return ResponseEntity.status(HttpStatus.OK).body("카테고리가 수정되었습니다");
    }

    @DeleteMapping("/todo/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        String username = ControllerUtil.getAuthenticatedUsername();
        categoryService.deleteCategory(id, username);

        return ResponseEntity.status(HttpStatus.OK).body("카테고리가 삭제되었습니다.");
    }

}
