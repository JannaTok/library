package kg.test_lesson.library.services;

import kg.test_lesson.library.entities.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author createAuthor(Author author);
    Author updateAuthor(Author author, Long id);
    void deleteAuthor(Long id);
}
