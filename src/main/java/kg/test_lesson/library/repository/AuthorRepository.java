package kg.test_lesson.library.repository;

import kg.test_lesson.library.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
