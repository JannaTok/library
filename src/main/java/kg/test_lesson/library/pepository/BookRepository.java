package kg.test_lesson.library.pepository;

import kg.test_lesson.library.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}
