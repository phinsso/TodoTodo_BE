package prj.todo.todotodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prj.todo.todotodo.dto.CategoryResponse;
import prj.todo.todotodo.dto.CreateCategoryRequest;
import prj.todo.todotodo.dto.UpdateCategoryRequest;
import prj.todo.todotodo.entity.Category;
import prj.todo.todotodo.entity.Member;
import prj.todo.todotodo.exception.CategoryExistException;
import prj.todo.todotodo.exception.CategoryNotFoundException;
import prj.todo.todotodo.repository.CategoryRepository;
import prj.todo.todotodo.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequest request, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 존재하지 않습니다."));

        boolean existingCategory = categoryRepository.existsByNameAndMemberUsername(request.getName(), username);

        if(existingCategory) {
            throw new CategoryExistException("동일한 이름을 가진 카테고리가 존재합니다.");
        }

        Category category = Category.builder()
                .name(request.getName())
                .member(member)
                .build();

        return categoryRepository.save(category);

    }
    public List<CategoryResponse> findAllCategories(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 존재하지 않습니다."));

        List<Category> categories = categoryRepository.findAllByMemberId(member.getId());

        return categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public Category updateCategory(Long id, UpdateCategoryRequest request, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 존재하지 않습니다."));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId())
                .orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));

        category.update(request.getName());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 가진 사용자가 존재하지 않습니다."));
        Category category = categoryRepository.findByIdAndMemberId(id, member.getId())
                .orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다"));

        categoryRepository.delete(category);
    }
}
