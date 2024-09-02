package kg.test_lesson.library.services.impl;

import kg.test_lesson.library.entities.Genres;
import kg.test_lesson.library.pepository.GenresRepository;
import kg.test_lesson.library.services.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenresRepository genresRepository;

    @Override
    public List<String> getGenresNames() {
        List<Genres> names = genresRepository.findAll();
        return names.stream().map(Genres::getName).collect(Collectors.toList());
    }
}
