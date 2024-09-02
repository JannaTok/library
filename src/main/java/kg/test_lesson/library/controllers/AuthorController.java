package kg.test_lesson.library.controllers;

import kg.test_lesson.library.entities.Author;
import kg.test_lesson.library.entities.Users;
import kg.test_lesson.library.services.AuthorService;
import kg.test_lesson.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final UserService userService;

    @GetMapping("/get")
    public String getAuthors(Model model, Principal principal) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);

        String username = principal.getName();
        Users user = userService.findByUsername(username);
        model.addAttribute("user", user);

        return "admin-home";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute Author author) {
        authorService.createAuthor(author);
        return "redirect:/admin/authors/get";
    }

    @PutMapping("/update")
    public Author updateAuthor(@RequestBody Author author,
                               @RequestParam Long id) {
        return authorService.updateAuthor(author, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/admin/authors/get";
    }
}