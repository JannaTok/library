package kg.test_lesson.library.repository;

import kg.test_lesson.library.entities.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres, Long> {
}
