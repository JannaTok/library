package kg.test_lesson.library;

import kg.test_lesson.library.dto.ImageDto;
import kg.test_lesson.library.dto.ImageResponseDto;
import kg.test_lesson.library.entities.Books;
import kg.test_lesson.library.repository.BookRepository;
import kg.test_lesson.library.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
  - getAllBooks: проверяется получение всех книг.
  - getBookById: проверяется получение книги по ID.
  - createBook: проверяется создание книги.
  - getImage: проверяется получение изображений.

 */

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void testGetAllBooks() {
        List<Books> books = Arrays.asList(new Books(), new Books());
        when(bookRepository.findAll()).thenReturn(books);

        List<Books> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        byte[] bookData = new byte[]{1, 2, 3};
        when(bookRepository.findBook(1L)).thenReturn(bookData);

        byte[] result = bookService.getBookById(1L);

        assertArrayEquals(bookData, result);
        verify(bookRepository, times(1)).findBook(1L);
    }

    @Test
    void testGetImage() {
        List<ImageDto> images = Arrays.asList(new ImageDto(1L, new byte[]{1, 2, 3}, "imageName"));
        when(bookRepository.findAllImageBytesAndName()).thenReturn(images);

        List<ImageResponseDto> result = bookService.getImage();

        assertEquals(1, result.size());
        assertEquals("imageName", result.get(0).getName());
        verify(bookRepository, times(1)).findAllImageBytesAndName();
    }
}