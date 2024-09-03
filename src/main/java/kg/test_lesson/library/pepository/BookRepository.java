package kg.test_lesson.library.pepository;

import kg.test_lesson.library.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Long> {
    @Query("SELECT b.imageBytes FROM Books b")
    List<byte[]> findAllImageBytes();
}
