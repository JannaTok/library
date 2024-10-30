package kg.test_lesson.library.services.impl;

import kg.test_lesson.library.entities.Author;
import kg.test_lesson.library.repository.AuthorRepository;
import kg.test_lesson.library.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author newAuthor, Long id) {
        Author oldAuthor = authorRepository.findById(id).orElseThrow();
        oldAuthor.setName(newAuthor.getName());
        oldAuthor.setDateOfBirth(newAuthor.getDateOfBirth());

        return authorRepository.save(oldAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
