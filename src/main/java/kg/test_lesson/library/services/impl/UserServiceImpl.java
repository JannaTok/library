package kg.test_lesson.library.services.impl;

import kg.test_lesson.library.entities.Users;
import kg.test_lesson.library.pepository.UserRepository;
import kg.test_lesson.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users findByUsername(String name) {
        return userRepository.findByLogin(name);
    }
}
