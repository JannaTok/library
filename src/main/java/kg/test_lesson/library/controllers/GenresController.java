package kg.test_lesson.library.controllers;

import kg.test_lesson.library.services.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    @GetMapping("/get-names")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<String> getGenresNames() {
        return genresService.getGenresNames();
    }
}
