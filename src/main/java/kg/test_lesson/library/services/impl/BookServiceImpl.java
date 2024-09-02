package kg.test_lesson.library.services.impl;

import kg.test_lesson.library.entities.Books;
import kg.test_lesson.library.pepository.BookRepository;
import kg.test_lesson.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books getBookById(Long id) {
        return null;
    }

    @Override
    public void createBook(Books book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, Books book) {

    }

    @Override
    public void deleteBook(Long id) {

    }
}
