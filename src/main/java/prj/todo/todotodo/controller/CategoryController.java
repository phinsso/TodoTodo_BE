package prj.todo.todotodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prj.todo.todotodo.dto.AddCategoryRequest;
import prj.todo.todotodo.dto.CategoryResponse;
import prj.todo.todotodo.dto.UpdateCategoryRequest;
import prj.todo.todotodo.entity.Category;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody AddCategoryRequest request) {
        Category created = categoryService.addCategory(request);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 카테고리 삭제
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        Category deleted = categoryService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 카테고리 목록 전체 조회
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> findAllCategory() {
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(categories);
    }

    // 카테고리 이름 수정
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id,
                                                   @RequestBody UpdateCategoryRequest request) {
        Category updated = categoryService.update(id, request);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
