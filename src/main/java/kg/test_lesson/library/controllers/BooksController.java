package kg.test_lesson.library.controllers;

import kg.test_lesson.library.dto.BookCreateDTO;
import kg.test_lesson.library.dto.ImageResponseDto;
import kg.test_lesson.library.entities.Books;
import kg.test_lesson.library.entities.Users;
import kg.test_lesson.library.mapper.BookMapper;
import kg.test_lesson.library.services.BookService;
import kg.test_lesson.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/get")
    public String getAllBooks(Model model, Principal principal) {
        List<Books> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        String userName = principal.getName();
        Users user = userService.findByUsername(userName);
        model.addAttribute("user", user);

        return "book";
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<byte[]> getBookById(@PathVariable Long id) {
        byte[] book = bookService.getBookById(id);
        if (book != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().build());
            return new ResponseEntity<>(book, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/create-book")
    public String createBook(@ModelAttribute BookCreateDTO book,
                             BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "redirect:/errorPage";  // Редирект на страницу с ошибкой
        }

        Books books = BookMapper.getModel(book);

        System.out.println("Content Size: " + (books.getContent() != null ? books.getContent().length : "null"));
        System.out.println("ImageBytes Size: " + (books.getImageBytes() != null ? books.getImageBytes().length : "null"));

        bookService.createBook(books);
        return "redirect:/books/get";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
//        Books book = bookService.getBookById(id);
//        model.addAttribute("book", book);
        return "book-edit";
    }

    @PutMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute Books book,
                             @RequestParam("contentFile") MultipartFile contentFile,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!contentFile.isEmpty()) {
            book.setContent(contentFile.getBytes());
        }
        if (!imageFile.isEmpty()) {
            book.setImageBytes(imageFile.getBytes());
        }
        bookService.updateBook(id, book);
        return "redirect:/books/get";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/get";
    }

    @GetMapping("/get-image")
    public ResponseEntity<List<ImageResponseDto>> getImage() {
        List<ImageResponseDto> image = bookService.getImage();
        if (!image.isEmpty()) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}