package kg.test_lesson.library.pepository;

import kg.test_lesson.library.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
