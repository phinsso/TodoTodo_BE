package prj.todo.todotodo.exception;

public class CategoryExistException extends RuntimeException {
    public CategoryExistException(String message) {
        super(message);
    }
}
