package kg.test_lesson.library;

import kg.test_lesson.library.controllers.BooksController;
import kg.test_lesson.library.dto.BookCreateDTO;
import kg.test_lesson.library.dto.ImageResponseDto;
import kg.test_lesson.library.entities.Books;
import kg.test_lesson.library.entities.Users;
import kg.test_lesson.library.services.BookService;
import kg.test_lesson.library.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*

  - Проверяются различные сценарии для методов контроллера:
  - getAllBooks: проверяет, что книги и пользователь добавляются в модель.
  - createBook: проверяются случаи с и без ошибок валидации.
  - getBookById: проверяются сценарии, когда книга найдена или не найдена.
  - getImage: проверяются сценарии с изображениями и без.

 */

public class BookControllerTest {

    @InjectMocks
    private BooksController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Инициализация моков
    }

    @Test
    void testGetAllBooks() {
        // Настройка данных для теста
        List<Books> books = Arrays.asList(new Books(), new Books());
        when(bookService.getAllBooks()).thenReturn(books);
        when(principal.getName()).thenReturn("user");
        Users user = new Users();
        when(userService.findByUsername("user")).thenReturn(user);

        // Выполнение теста
        String view = bookController.getAllBooks(model, principal);

        // Проверка результатов
        assertEquals("book", view);
        verify(model).addAttribute("books", books);
        verify(model).addAttribute("user", user);
    }

    @Test
    void testCreateBook_Success() throws Exception {
        BookCreateDTO bookDTO = new BookCreateDTO();
        Books books = new Books();
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(bookService).createBook(any(Books.class));

        String view = bookController.createBook(bookDTO, bindingResult);

        assertEquals("redirect:/books/get", view);
        verify(bookService).createBook(any(Books.class));
    }

    @Test
    void testCreateBook_WithErrors() throws Exception {
        BookCreateDTO bookDTO = new BookCreateDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = bookController.createBook(bookDTO, bindingResult);

        assertEquals("redirect:/errorPage", view);
        verify(bookService, never()).createBook(any(Books.class));
    }

    @Test
    void testGetBookById() {
        byte[] bookBytes = new byte[]{1, 2, 3};
        when(bookService.getBookById(1L)).thenReturn(bookBytes);

        ResponseEntity<byte[]> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(bookBytes, response.getBody());
        assertEquals("application/pdf", response.getHeaders().getContentType().toString());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookService.getBookById(1L)).thenReturn(null);

        ResponseEntity<byte[]> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetImage() {
        List<ImageResponseDto> images = Arrays.asList(new ImageResponseDto(1L, "imageData", "imageName"));
        when(bookService.getImage()).thenReturn(images);
        ResponseEntity<List<ImageResponseDto>> response = bookController.getImage();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(images, response.getBody());
    }

    @Test
    void testGetImage_NoContent() {
        when(bookService.getImage()).thenReturn(Arrays.asList());

        ResponseEntity<List<ImageResponseDto>> response = bookController.getImage();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}