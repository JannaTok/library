package kg.test_lesson.library.repository;

import kg.test_lesson.library.dto.ImageDto;
import kg.test_lesson.library.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Long> {
    @Query("SELECT new kg.test_lesson.library.dto.ImageDto(b.id, b.imageBytes, b.name) FROM Books b")
    List<ImageDto> findAllImageBytesAndName();

    @Query("SELECT b.content FROM Books b where b.id = :id") //JPQL
    byte[] findBook(@Param("id") Long id);
}
