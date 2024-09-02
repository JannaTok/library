package kg.test_lesson.library.services;

import kg.test_lesson.library.entities.Users;

public interface UserService {
    Users findByUsername(String name);
}
