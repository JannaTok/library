package kg.test_lesson.library.services;

import kg.test_lesson.library.entities.Books;

import java.util.List;

public interface BookService {
    List<Books> getAllBooks();
    Books getBookById(Long id);
    void createBook(Books book);
    void updateBook(Long id, Books book);
    void deleteBook(Long id);
}
